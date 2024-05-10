package com.artemissoftware.shoppingcart.presentation.additem

sealed class AddProductEvent {
    object BuyProduct: AddProductEvent()
    object AddQuantity: AddProductEvent()
    object RemoveQuantity: AddProductEvent()
}