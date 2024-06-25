package com.artemissoftware.shoppingcart.domain.models

sealed class SnackBarState(val title: String) {
    data class Info(val titleDescription: String): SnackBarState(title = titleDescription)
}