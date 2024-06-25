package com.artemissoftware.shoppingcart.presentation.addproduct

sealed class AddProductEvent {
    object BuyProduct: AddProductEvent()
    object AddQuantity: AddProductEvent()
    object RemoveQuantity: AddProductEvent()
}