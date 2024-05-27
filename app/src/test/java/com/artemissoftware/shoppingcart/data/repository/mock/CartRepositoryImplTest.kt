package com.artemissoftware.shoppingcart.data.repository.mock

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import com.artemissoftware.shoppingcart.ProductTestData.emptyImagesDto
import com.artemissoftware.shoppingcart.ProductTestData.getProduct
import com.artemissoftware.shoppingcart.ProductTestData.imageDto
import com.artemissoftware.shoppingcart.data.database.dao.ProductDao
import com.artemissoftware.shoppingcart.data.mapper.toEntity
import com.artemissoftware.shoppingcart.data.network.source.PixabayApiSource
import com.artemissoftware.shoppingcart.data.repository.CartRepositoryImpl
import com.artemissoftware.shoppingcart.domain.Resource
import com.artemissoftware.shoppingcart.domain.error.DataError
import com.artemissoftware.shoppingcart.domain.error.ImagesError
import com.artemissoftware.shoppingcart.domain.models.Product
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import retrofit2.HttpException
import java.util.stream.Stream


internal class CartRepositoryImplTest{

    private lateinit var productDao: ProductDao
    private lateinit var pixabayApiSource: PixabayApiSource

    private lateinit var cartRepository: CartRepositoryImpl

    @BeforeEach
    fun setUp(){
        productDao = mockk()
        pixabayApiSource = mockk()

        cartRepository = CartRepositoryImpl(
            pixabayApiSource = pixabayApiSource,
            productDao = productDao
        )
    }

    @Test
    fun `Save a product`() = runBlocking {

        val product = getProduct()
        val productEntity = product.toEntity()

        coEvery { productDao.insert(productEntity = productEntity) } returns Unit
        coEvery { productDao.getAll() } returns flowOf(listOf(productEntity))

        cartRepository.saveProduct(product)

        val result = cartRepository.getCart().first()
        coVerify(exactly = 1) { productDao.insert(productEntity = productEntity) }
        coVerify(exactly = 1) { productDao.getAll() }

        assertThat(result)
            .isEqualTo(listOf(product))
    }

    @ParameterizedTest(name = "carts total {1}, items {0}")
    @MethodSource("carts")
    fun `Save products, get cart total price`(products: List<Product>, totalPrice: Double) = runBlocking {

        coEvery { productDao.getTotalPrice() } returns flowOf(totalPrice)

        products.forEach {
            coEvery { productDao.insert(productEntity = it.toEntity()) } returns Unit
            cartRepository.saveProduct(it)
        }

        val result = cartRepository.getCartTotal().first()

        coVerify(exactly = products.size) { productDao.insert(productEntity = any()) }
        coVerify(exactly = 1) { productDao.getTotalPrice() }

        assertThat(result)
            .isEqualTo(totalPrice)
    }

    @Test
    fun `Search a product, find product`() = runBlocking {

        coEvery { pixabayApiSource.getImages(any()) } returns imageDto

        val result = cartRepository.searchProducts(searchQuery = "christmas")

        coVerify(exactly = 1) { pixabayApiSource.getImages(any()) }

        assertThat(actual = result is Resource.Success<List<Product>>)
            .isTrue()

        assertThat((result as Resource.Success).data)
            .hasSize(imageDto.hits.size)
    }

    @Test
    fun `Search a product, don't find product`() = runBlocking {

        coEvery { pixabayApiSource.getImages(any()) } returns emptyImagesDto

        val result = cartRepository.searchProducts(searchQuery = "love")

        assertThat(actual = result is Resource.Success<List<Product>>)
            .isTrue()

        assertThat((result as Resource.Success).data)
            .hasSize(0)
    }

    @Test
    fun `Search a product, return error`() = runBlocking {
        coEvery { pixabayApiSource.getImages(searchQuery = any()) } throws mockk<HttpException>{
            every { code() } returns 404
            every { message() } returns "Test message"
        }

        val result = cartRepository.searchProducts(searchQuery = "love")

        coVerify(exactly = 1) { pixabayApiSource.getImages(searchQuery = any()) }

        assertThat(actual = result is Resource.Failure)
            .isTrue()

        assertThat((result as Resource.Failure).error)
            .isEqualTo(DataError.NetworkError.Unknown)
    }

    @Test
    fun `Get a product, find product in database`() = runBlocking {

        val id = 1
        val product = getProduct(id = id)
        val productEntity = product.toEntity()

        coEvery { productDao.insert(productEntity = productEntity) } returns Unit
        coEvery { productDao.get(id = id) } returns productEntity

        cartRepository.saveProduct(getProduct(id = id))

        val result = cartRepository.getProduct(id = id)

        coVerify(exactly = 1) { productDao.insert(productEntity = productEntity) }
        coVerify(exactly = 1) { productDao.get(id = id) }
        coVerify(exactly = 0) { pixabayApiSource.getImageById(id = id.toString()) }

        assertThat(actual = result is Resource.Success<Product>)
            .isTrue()

        assertThat((result as Resource.Success).data)
            .isEqualTo(getProduct(id = 1))
    }

    @Test
    fun `Get a product, find product in api`() = runBlocking {
        val id = 2953719

        coEvery { productDao.get(id = id) } returns null
        coEvery { pixabayApiSource.getImageById(id = id.toString()) } returns imageDto

        val result = cartRepository.getProduct(id = id)

        coVerify(exactly = 1) { productDao.get(id = id) }
        coVerify(exactly = 1) { pixabayApiSource.getImageById(id = id.toString()) }

        assertThat(actual = result is Resource.Success<Product>)
            .isTrue()
    }

    @Test
    fun `Get a product, api does not find it`() = runBlocking {
        val id = 20

        coEvery { productDao.get(id = id) } returns null
        coEvery { pixabayApiSource.getImageById(id = id.toString()) } returns emptyImagesDto

        val result = cartRepository.getProduct(id = id)

        coVerify(exactly = 1) { productDao.get(id = id) }
        coVerify(exactly = 1) { pixabayApiSource.getImageById(id = id.toString()) }

        assertThat(actual = result is Resource.Failure)
            .isTrue()

        assertThat((result as Resource.Failure).error)
            .isEqualTo(ImagesError.SearchError.NoImagesFound)
    }

    @Test
    fun `Get a product, api returns error`() = runBlocking {

        coEvery { pixabayApiSource.getImageById(id = any()) } throws mockk<HttpException>{
            every { code() } returns 404
            every { message() } returns "Test message"
        }

        coEvery { productDao.get(id = any()) } returns null

        val result = cartRepository.getProduct(id = 20)

        coVerify(exactly = 1) { productDao.get(id = any()) }
        coVerify(exactly = 1) { pixabayApiSource.getImageById(id = any()) }

        assertThat(actual = result is Resource.Failure)
            .isTrue()

        assertThat((result as Resource.Failure).error)
            .isEqualTo(DataError.NetworkError.Unknown)
    }

    private companion object {

        private val productList1 = listOf(getProduct())
        private val productList2 = listOf(getProduct(), getProduct(), getProduct())
        private val productList3 = listOf(getProduct(price = 200.0, quantity = 3), getProduct(price = 500.0, quantity = 13))

        @JvmStatic
        fun carts(): Stream<Arguments> = Stream.of(
            Arguments.of(productList1, productList1.sumOf { it.totalPrice() }),
            Arguments.of(productList2, productList2.sumOf { it.totalPrice() }),
            Arguments.of(productList3, productList3.sumOf { it.totalPrice() })
        )
    }
}