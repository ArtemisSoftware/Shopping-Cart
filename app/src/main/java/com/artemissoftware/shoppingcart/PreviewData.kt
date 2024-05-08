package com.artemissoftware.shoppingcart

import com.artemissoftware.shoppingcart.domain.models.Product

object PreviewData {

    val product =
        Product(
            "Office Code",
            "\$234",
            "Lorem Ipsum is simply dummy text of " +
                    "the printing and typesetting industry. " +
                    "Lorem Ipsum has been the industry's standard dummy text ever since. " +
                    "When an unknown printer took a galley.",
            R.drawable.office_code)
}