package com.artemissoftware.shoppingcart.presentation.searchproduct

sealed class SearchProductEvent {
    data class UpdateQuery(val query: String): SearchProductEvent()
    object Search: SearchProductEvent()
}