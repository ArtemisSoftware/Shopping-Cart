package com.artemissoftware.domain.error

sealed interface ProductError : SCError {

    sealed class SearchError : ProductError {
        object NoImagesFound : SearchError()
    }
}