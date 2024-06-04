package com.artemissoftware.shoppingcart.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.artemissoftware.shoppingcart.data.database.dao.ProductDao
import com.artemissoftware.shoppingcart.data.database.entities.ProductEntity

@Database(
    entities = [
                    ProductEntity::class
               ],
    version = 3,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = ShoppingCartDatabase.Migration2To3::class),
    ]
)
abstract class ShoppingCartDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    @RenameColumn(tableName = "products", fromColumnName = "comments", toColumnName = "commentary")
    class Migration2To3: AutoMigrationSpec

    companion object {
        const val DATABASE_NAME = "shopping_cart_db"
    }
}