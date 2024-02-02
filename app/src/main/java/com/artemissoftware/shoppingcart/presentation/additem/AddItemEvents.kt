package com.artemissoftware.shoppingcart.presentation.additem

sealed class AddItemEvents {

    data class InsertItem(val name: String, val amount: String, val price: String, val imageUrl: String)
}
