package com.artemissoftware.data.repository

import com.artemissoftware.data.HandleNetwork
import com.artemissoftware.data.mapper.toEntity
import com.artemissoftware.data.mapper.toProduct
import com.artemissoftware.database.dao.ProductDao
import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.error.ProductError
import com.artemissoftware.domain.repository.CartRepository
import com.artemissoftware.models.Product
import com.artemissoftware.network.source.PixabayApiSource
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

        val result = productDao.get(id)?.toProduct()

        return if(result != null){
            Resource.Success(result)
        } else {
            getImageById(id = id)
        }
    }

    private suspend fun getImageById(id: Int): Resource<Product>{
        val result = HandleNetwork.safeNetworkCall {
            pixabayApiSource.getImageById(id = id.toString()).hits.toProduct()
        }

        return when(result){
            is Resource.Failure -> Resource.Failure(result.error)
            is Resource.Success -> {
                result.data?.let {
                    Resource.Success(it)
                } ?: run {
                    Resource.Failure(ProductError.SearchError.NoImagesFound)
                }
            }
        }
    }
}