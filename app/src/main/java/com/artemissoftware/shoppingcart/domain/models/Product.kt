package com.artemissoftware.shoppingcart.domain.models

import androidx.annotation.DrawableRes

data class Product(
    val title: String,
    val price: String,
    val description: String,
    @DrawableRes val img: Int
)


