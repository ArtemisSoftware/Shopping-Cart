package com.artemissoftware.shoppingcart.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sellers")
data class SellerEntity(
    @PrimaryKey
    val uuid: String,
    val name: String,
    val imageUrl: String
)
