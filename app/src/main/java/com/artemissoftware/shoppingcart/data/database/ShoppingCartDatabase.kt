package com.artemissoftware.shoppingcart.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.artemissoftware.shoppingcart.data.database.dao.ProductDao
import com.artemissoftware.shoppingcart.data.database.entities.ProductEntity
import com.artemissoftware.shoppingcart.data.database.entities.SellerEntity
import com.artemissoftware.shoppingcart.data.database.migrations.Migration2To3

@Database(
    entities = [
                    ProductEntity::class,
                    SellerEntity::class,
               ],
    version = 4,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = Migration2To3::class),
    ]
)
abstract class ShoppingCartDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    companion object {
        const val DATABASE_NAME = "shopping_cart_db"
    }
}