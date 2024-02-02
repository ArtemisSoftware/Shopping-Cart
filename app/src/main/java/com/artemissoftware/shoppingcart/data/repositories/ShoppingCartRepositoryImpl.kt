package com.artemissoftware.shoppingcart.data.repositories

import com.artemissoftware.shoppingcart.data.database.ShoppingDao
import com.artemissoftware.shoppingcart.data.mappers.toEntity
import com.artemissoftware.shoppingcart.domain.models.ShoppingItem
import com.artemissoftware.shoppingcart.domain.repositories.ShoppingCartRepository
import javax.inject.Inject

class ShoppingCartRepositoryImpl @Inject constructor(
    private val shoppingDao: ShoppingDao,
    // private val pixabayApi: PixabayApi
) : ShoppingCartRepository {
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem.toEntity())
    }
}
