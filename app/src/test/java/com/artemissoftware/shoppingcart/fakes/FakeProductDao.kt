package com.artemissoftware.shoppingcart.fakes

import com.artemissoftware.shoppingcart.data.database.dao.ProductDao
import com.artemissoftware.shoppingcart.data.database.entities.ProductEntity
import com.artemissoftware.shoppingcart.data.database.entities.SellerEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class FakeProductDao : ProductDao {

    private val products = MutableStateFlow<List<ProductEntity>>(emptyList())
    private val sellers = mutableListOf<SellerEntity>()

    override suspend fun insert(productEntity: ProductEntity) {
        val list = products.value.toMutableList()
        list.add(productEntity)
        products.value = list
    }

    override suspend fun insert(sellerEntity: SellerEntity) {
        sellers.add(sellerEntity)
    }

    override suspend fun delete(productEntity: ProductEntity) {
        val list = products.value.toMutableList()
        list.remove(productEntity)
        products.value = list
    }

    override suspend fun get(id: Int): ProductEntity? {
        return products.value.find { it.id == id }
    }

    override fun getAll(): Flow<List<ProductEntity>> {
        return products
    }

    override fun getTotalPrice(): Flow<Double> {
        return flow { emit(products.value.sumOf { it.price * it.amount })  }
    }
}