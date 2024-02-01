package com.artemissoftware.shoppingcart.data.database

import androidx.room.Database

@Database(
    entities = [ShoppingItemEntity::class],
    version = 1,
)
abstract class ShoppingCartDatabase {

    abstract fun shoppingDao(): ShoppingDao
}
