package com.artemissoftware.shoppingcart.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.artemissoftware.shoppingcart.data.database.dao.ProductDao
import com.artemissoftware.shoppingcart.data.database.entities.ProductEntity

@Database(
    entities = [
                    ProductEntity::class
               ],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
abstract class ShoppingCartDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    companion object {
        const val DATABASE_NAME = "shopping_cart_db"
    }
}