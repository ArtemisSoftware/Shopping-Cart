package com.artemissoftware.shoppingcart.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ShoppingItemEntity::class],
    version = 1,
)
abstract class ShoppingCartDatabase : RoomDatabase() {

    abstract fun shoppingDao(): ShoppingDao
}
