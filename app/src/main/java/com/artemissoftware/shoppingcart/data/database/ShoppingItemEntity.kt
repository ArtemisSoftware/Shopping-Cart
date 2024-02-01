package com.artemissoftware.shoppingcart.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shoppingItems")
data class ShoppingItemEntity(
    var name: String,
    var amount: Int,
    var price: Float,
    var imageUrl: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
)
