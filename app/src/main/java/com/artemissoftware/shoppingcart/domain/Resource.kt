package com.artemissoftware.shoppingcart.domain

import com.artemissoftware.shoppingcart.domain.error.SCError

sealed interface Resource<T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Failure<T>(val error: SCError) : Resource<T>

    fun onSuccess(block: (T) -> Unit): Resource<T> {
        if (this is Success) block(data)
        return this
    }

    fun onFailure(block: (SCError) -> Unit): Resource<T> {
        if (this is Failure) block(error)
        return this
    }
}