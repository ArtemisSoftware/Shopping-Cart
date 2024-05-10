package com.artemissoftware.shoppingcart.domain.models

import androidx.annotation.DrawableRes

data class Product(
    val title: String,
    val quantity: Int,
    val price: Double,
    val description: String,
    @DrawableRes val img: Int
){
    fun totalPrice() = quantity * price
}


