package com.artemissoftware.shoppingcart.domain.models

import androidx.annotation.DrawableRes

data class Product(
    val id: Int,
    val title: String,
    val quantity: Int,
    val price: Double,
    val description: String,
    val imageUrl: String,
    val previewUrl: String,
    @DrawableRes val img: Int = -1
){
    fun totalPrice() = quantity * price
}


