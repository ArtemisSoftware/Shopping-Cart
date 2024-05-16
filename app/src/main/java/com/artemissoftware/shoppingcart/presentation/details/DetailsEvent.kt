package com.artemissoftware.shoppingcart.presentation.details

sealed class DetailsEvent {
    data class Save(val comment: String, val promoCode: String): DetailsEvent()
}