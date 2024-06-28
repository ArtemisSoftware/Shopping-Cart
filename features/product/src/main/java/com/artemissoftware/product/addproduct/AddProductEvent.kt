package com.artemissoftware.product.addproduct

sealed class AddProductEvent {
    object BuyProduct: AddProductEvent()
    object AddQuantity: AddProductEvent()
    object RemoveQuantity: AddProductEvent()
}