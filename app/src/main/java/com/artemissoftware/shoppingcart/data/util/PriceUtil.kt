package com.artemissoftware.shoppingcart.data.util

import kotlin.random.Random

internal object PriceUtil {

    fun generateRandomPrice(minPrice: Double, maxPrice: Double): Double {
        require(minPrice < maxPrice) { "minPrice must be less than maxPrice" }

        val randomPrice = Random.nextDouble(minPrice, maxPrice)
        // Round to two decimal places
        return String.format("%.2f", randomPrice).replace(",", ".").toDouble()
    }
}