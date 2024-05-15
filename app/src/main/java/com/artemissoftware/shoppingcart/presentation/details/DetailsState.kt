package com.artemissoftware.shoppingcart.presentation.details

import com.artemissoftware.shoppingcart.domain.models.Product

data class DetailsState(
    val product: Product? = null,
    val notes: String = "",
)
