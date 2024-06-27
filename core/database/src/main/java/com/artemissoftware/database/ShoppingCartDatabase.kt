package com.artemissoftware.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.artemissoftware.database.dao.ProductDao
import com.artemissoftware.database.entities.ProductEntity
import com.artemissoftware.database.entities.SellerEntity
import com.artemissoftware.database.migrations.AutomaticMigration

@Database(
    entities = [
                    ProductEntity::class,
                    SellerEntity::class,
               ],
    version = 5,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = AutomaticMigration.Migration2To3::class),
        AutoMigration(from = 4, to = 5),
    ]
)
abstract class ShoppingCartDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    companion object {
        const val DATABASE_NAME = "shopping_cart_db"
    }
}