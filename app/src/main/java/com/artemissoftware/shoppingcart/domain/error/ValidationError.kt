package com.artemissoftware.shoppingcart.domain.error

sealed interface ValidationError : SCError {

    sealed class DetailError: ValidationError{

        object EmptyField : DetailError()

        object TooManyCharacters : DetailError()

        object InvalidAmount : DetailError()
    }
}