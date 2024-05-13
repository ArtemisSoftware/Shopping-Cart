package com.artemissoftware.shoppingcart.data.database.entities

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
    val previewUrl: String,
)
