package com.artemissoftware.shoppingcart.domain.models

import androidx.annotation.DrawableRes
import com.artemissoftware.shoppingcart.R

data class Product(
    val id: Int,
    val title: String,
    val quantity: Int,
    val price: Double,
    val description: String,
    val imageUrl: String,
    @DrawableRes val img: Int = R.drawable.office_code
){
    fun totalPrice() = quantity * price
}


