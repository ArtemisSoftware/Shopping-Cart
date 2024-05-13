package com.artemissoftware.shoppingcart.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.artemissoftware.shoppingcart.data.database.dao.ProductDao
import com.artemissoftware.shoppingcart.data.database.entities.ProductEntity

@Database(
    entities = [
                    ProductEntity::class
               ],
    version = 1,
)
abstract class ShoppingCartDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        const val DATABASE_NAME = "shopping_cart_db"
    }
}