package com.artemissoftware.data.repository

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import com.artemissoftware.data.TestData.getProduct
import com.artemissoftware.data.fakes.FakePixabayApi
import com.artemissoftware.data.fakes.FakeProductDao
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.error.DataError
import com.artemissoftware.domain.error.ProductError
import com.artemissoftware.models.Product
import com.artemissoftware.network.source.PixabayApiSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


internal class CartRepositoryImplTest{

    private lateinit var pixabayApi: FakePixabayApi
    private lateinit var productDao: FakeProductDao
    private lateinit var pixabayApiSource: PixabayApiSource

    private lateinit var cartRepository: CartRepositoryImpl

    @BeforeEach
    fun setUp(){
        pixabayApi = FakePixabayApi()
        productDao = FakeProductDao()
        pixabayApiSource = PixabayApiSource(pixabayApi = pixabayApi)

        cartRepository = CartRepositoryImpl(
            pixabayApiSource = pixabayApiSource,
            productDao = productDao
        )
    }

    @Test
    fun `Save a product`() = runBlocking {

        cartRepository.saveProduct(getProduct())

        val result = cartRepository.getCart().first()

        assertThat(result)
            .isEqualTo(listOf(getProduct()))
    }

    @ParameterizedTest(name = "carts total {1}, items {0}")
    @MethodSource("carts")
    fun `Save products, get cart total price`(products: List<Product>, totalPrice: Double) = runBlocking {

        products.forEach {
            cartRepository.saveProduct(it)
        }

        val result = cartRepository.getCartTotal().first()

        assertThat(result)
            .isEqualTo(totalPrice)
    }

    @Test
    fun `Search a product, find product`() = runBlocking {
        val result = cartRepository.searchProducts(searchQuery = "christmas")

        assertThat(actual = result is Resource.Success<List<Product>>)
            .isTrue()

        assertThat((result as Resource.Success).data)
            .hasSize(1)
    }

    @Test
    fun `Search a product, don't find product`() = runBlocking {
        val result = cartRepository.searchProducts(searchQuery = "love")

        assertThat(actual = result is Resource.Success<List<Product>>)
            .isTrue()

        assertThat((result as Resource.Success).data)
            .hasSize(0)
    }

    @Test
    fun `Search a product, return error`() = runBlocking {
        pixabayApi.shouldReturnError = true
        val result = cartRepository.searchProducts(searchQuery = "love")

        assertThat(actual = result is Resource.Failure)
            .isTrue()

        assertThat((result as Resource.Failure).error)
            .isEqualTo(DataError.NetworkError.Unknown)
    }

    @Test
    fun `Get a product, find product in database`() = runBlocking {
        cartRepository.saveProduct(getProduct(id = 1))

        val result = cartRepository.getProduct(id = 1)

        assertThat(actual = result is Resource.Success<Product>)
            .isTrue()

        assertThat((result as Resource.Success).data)
            .isEqualTo(getProduct(id = 1))
    }

    @Test
    fun `Get a product, find product in api`() = runBlocking {
        val result = cartRepository.getProduct(id = 2953719)

        assertThat(actual = result is Resource.Success<Product>)
            .isTrue()
    }

    @Test
    fun `Get a product, api does not find it`() = runBlocking {
        val result = cartRepository.getProduct(id = 20)

        assertThat(actual = result is Resource.Failure)
            .isTrue()

        assertThat((result as Resource.Failure).error)
            .isEqualTo(ProductError.SearchError.NoImagesFound)
    }

    @Test
    fun `Get a product, api returns error`() = runBlocking {
        pixabayApi.shouldReturnError = true
        val result = cartRepository.getProduct(id = 20)

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