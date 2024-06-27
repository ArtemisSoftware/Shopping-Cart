package com.artemissoftware.models

sealed class SnackBarState(val title: String) {
    data class Info(val titleDescription: String): SnackBarState(title = titleDescription)
}