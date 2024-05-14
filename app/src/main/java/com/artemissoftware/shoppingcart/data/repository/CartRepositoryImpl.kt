package com.artemissoftware.shoppingcart.data.repository

import com.artemissoftware.shoppingcart.data.HandleNetwork
import com.artemissoftware.shoppingcart.data.database.dao.ProductDao
import com.artemissoftware.shoppingcart.data.mapper.toEntity
import com.artemissoftware.shoppingcart.data.mapper.toProduct
import com.artemissoftware.shoppingcart.data.network.source.PixabayApiSource
import com.artemissoftware.shoppingcart.domain.repository.CartRepository
import com.artemissoftware.shoppingcart.domain.Resource
import com.artemissoftware.shoppingcart.domain.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val pixabayApiSource: PixabayApiSource,
    private val productDao: ProductDao,
): CartRepository {
    override fun getCart(): Flow<List<Product>> = productDao.getAll().map { list -> list.map { it.toProduct() } }
    override fun getCartTotal(): Flow<Double> = productDao.getTotalPrice()

    override suspend fun saveProduct(product: Product) {
        productDao.insert(product.toEntity())
    }

    override suspend fun searchProducts(searchQuery: String): Resource<List<Product>> {
        return HandleNetwork.safeNetworkCall {
            pixabayApiSource.getImages(searchQuery = searchQuery).hits.map { it.toProduct(searchQuery) }
        }
    }

    override suspend fun getProduct(id: Int): Resource<Product> {
        return HandleNetwork.safeNetworkCall {
            pixabayApiSource.getImageById(id = id.toString()).hits.first().toProduct()
        }
    }
}