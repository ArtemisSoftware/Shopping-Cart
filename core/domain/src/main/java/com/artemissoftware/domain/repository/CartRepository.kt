package com.artemissoftware.domain.repository

import com.artemissoftware.domain.Resource
import com.artemissoftware.models.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getCart(): Flow<List<Product>>

    fun getCartTotal(): Flow<Double>

    suspend fun saveProduct(product: Product)

    suspend fun searchProducts(searchQuery: String): Resource<List<Product>>

    suspend fun getProduct(id: Int): Resource<Product>
}