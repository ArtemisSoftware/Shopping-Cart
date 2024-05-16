package com.artemissoftware.shoppingcart.data.util

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isBetween
import org.junit.jupiter.api.Test


internal class PriceUtilTest{

    @Test
    fun `generate a price between 100 and 1000`(){
        val result = PriceUtil.generateRandomPrice(minPrice = 100.0, maxPrice = 1000.0)

        assertThat(result).isBetween(100.0, 1000.0)
    }

    @Test
    fun `generate a price between max is 100 and min is 1000, should throw exception`(){
        assertFailure {
            PriceUtil.generateRandomPrice(minPrice = 1000.0, maxPrice = 100.0)
        }
    }
}