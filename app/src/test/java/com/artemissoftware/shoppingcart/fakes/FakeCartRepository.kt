package com.artemissoftware.shoppingcart.fakes

import com.artemissoftware.shoppingcart.domain.Resource
import com.artemissoftware.shoppingcart.domain.error.ProductError
import com.artemissoftware.shoppingcart.domain.error.SCError
import com.artemissoftware.shoppingcart.domain.models.Product
import com.artemissoftware.shoppingcart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

internal class FakeCartRepository(productList: List<Product> = emptyList()): CartRepository {

    private val products = MutableStateFlow<List<Product>>(productList)
    var errorToReturn: SCError? = null

    override fun getCart(): Flow<List<Product>> {
        return products
    }

    override fun getCartTotal(): Flow<Double> {
        return flow { emit(products.value.sumOf { it.totalPrice() })  }
    }

    override suspend fun saveProduct(product: Product) {
        val list = products.value.toMutableList()
        list.add(product)
        products.value = list
    }

    override suspend fun searchProducts(searchQuery: String): Resource<List<Product>> {
        return if(errorToReturn != null){
            Resource.Failure(errorToReturn!!)
        } else {
            Resource.Success(products.value.filter { it.title == searchQuery.capitalize() })
        }
    }

    override suspend fun getProduct(id: Int): Resource<Product> {
        return if(errorToReturn != null){
            Resource.Failure(errorToReturn!!)
        } else {
            products.value.find { it.id == id }?.let {
                Resource.Success(it)
            } ?: run {
                Resource.Failure(ProductError.SearchError.NoImagesFound)
            }
        }
    }
}