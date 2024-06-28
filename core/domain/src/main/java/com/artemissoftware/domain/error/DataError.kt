package com.artemissoftware.domain.error

sealed interface DataError : SCError {

    sealed class NetworkError : DataError {
        data class Error(val message: String) : NetworkError()
        object SocketTimeout : NetworkError()
        object Unknown : NetworkError()
        object UnknownHost : NetworkError()
        object Connect : NetworkError()
    }
}