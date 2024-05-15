package com.artemissoftware.shoppingcart.presentation.details

sealed class DetailsEvent {
    object Save: DetailsEvent()
    data class UpdateNote(val note: String): DetailsEvent()
}