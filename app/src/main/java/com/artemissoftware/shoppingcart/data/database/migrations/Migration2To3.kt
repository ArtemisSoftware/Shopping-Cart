package com.artemissoftware.shoppingcart.data.database.migrations

import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec

@RenameColumn(tableName = "products", fromColumnName = "comments", toColumnName = "commentary")
class Migration2To3: AutoMigrationSpec