package com.artemissoftware.shoppingcart.data.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object ManualMigration {
    val migration3To4 = object : Migration(3,4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                """
                    CREATE TABLE IF NOT EXISTS sellers (
                        uuid CHAR NOT NULL PRIMARY KEY, 
                        name TEXT NOT NULL, 
                        imageUrl TEXT NOT NULL
                    )
                """.trimIndent())
        }
    }

    val ALL_MIGRATIONS = arrayOf(
        migration3To4
    )
}