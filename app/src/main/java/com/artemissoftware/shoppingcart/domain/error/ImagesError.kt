package com.artemissoftware.shoppingcart.domain.error

sealed interface ImagesError : SCError {

    sealed class SearchError : ImagesError {
        object NoImagesFound : SearchError()
    }
}