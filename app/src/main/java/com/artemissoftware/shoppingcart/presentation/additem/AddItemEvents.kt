package com.artemissoftware.shoppingcart.presentation.additem

sealed class AddItemEvents {
    data class UpdateName(val name: String) : AddItemEvents()
    data class UpdateAmount(val amount: String) : AddItemEvents()
    data class UpdatePrice(val price: String) : AddItemEvents()
    data class UpdateImageUrl(val imageUrl: String) : AddItemEvents()
    object InsertItem : AddItemEvents()
}
