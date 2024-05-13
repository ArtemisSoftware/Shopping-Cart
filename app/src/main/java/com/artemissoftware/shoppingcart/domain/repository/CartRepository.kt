package com.artemissoftware.shoppingcart.domain.repository

import com.artemissoftware.shoppingcart.domain.Resource
import com.artemissoftware.shoppingcart.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCart(): Flow<List<Product>>

    suspend fun saveProduct(product: Product)

    suspend fun searchProducts(searchQuery: String): Resource<List<Product>>

}