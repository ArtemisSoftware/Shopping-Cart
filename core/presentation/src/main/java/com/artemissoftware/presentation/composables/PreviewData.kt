package com.artemissoftware.presentation.composables

import com.artemissoftware.models.Product

internal object PreviewData {

    val product =
        Product(
            id = 1,
            title = "Office Code",
            quantity = 2,
            price =234.0,
            imageUrl = "",
            description = "Lorem Ipsum is simply dummy text of " +
                    "the printing and typesetting industry. " +
                    "Lorem Ipsum has been the industry's standard dummy text ever since. " +
                    "When an unknown printer took a galley."
        )
}