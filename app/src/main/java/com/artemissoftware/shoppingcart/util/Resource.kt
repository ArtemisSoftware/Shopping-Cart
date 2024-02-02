package com.artemissoftware.shoppingcart.util

sealed class Resource<T>(val value: T? = null, val message: String?) {
    data class Success<T>(val data: T? = null) : Resource<T>(value = data, message = null)
    data class Error<T>(val msg: String, val data: T?) : Resource<T>(value = data, message = msg)
    data class Loading<T>(val data: T? = null) : Resource<T>(value = data, message = null)
}
