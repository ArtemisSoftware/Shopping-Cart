package com.artemissoftware.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val amount: Int,
    val price: Double,
    val imageUrl: String,
    @ColumnInfo(name = "comments", defaultValue = "N/A")
    val commentary: String,
    @ColumnInfo(name = "sellerId")
    val sellerId: String? = null
)
