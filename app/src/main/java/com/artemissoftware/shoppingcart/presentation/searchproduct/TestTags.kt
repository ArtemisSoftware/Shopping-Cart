package com.artemissoftware.shoppingcart.presentation.searchproduct

internal object TestTags {
    const val SEARCH_PRODUCT_SEARCH_BAR = "search_product_search_bar"
    const val SEARCH_PRODUCT_SEARCH_ICON = "search_product_search_icon"
    const val SEARCH_PRODUCT_GRID = "search_product_grid"

    private const val SEARCH_PRODUCT_ITEM = "search_product_item"
    fun searchProductItemTag(id: Int) = SEARCH_PRODUCT_ITEM + id

    const val SEARCH_PRODUCT_ERROR = "search_product_error"
}