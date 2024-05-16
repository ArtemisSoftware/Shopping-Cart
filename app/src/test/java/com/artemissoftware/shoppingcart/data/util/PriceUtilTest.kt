package com.artemissoftware.shoppingcart.data.util

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isBetween
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


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
    
    @ParameterizedTest(name = "price in range [{0},{1}]")
    @MethodSource("priceIntervals")
    fun `generate a price between multiple different intervals`(minPrice: Double, maxPrice: Double){
        val result = PriceUtil.generateRandomPrice(minPrice = minPrice, maxPrice = maxPrice)

        assertThat(result).isBetween(minPrice, maxPrice)
    }

    private companion object {
        @JvmStatic
        fun priceIntervals(): Stream<Arguments> = Stream.of(
            Arguments.of(10.0, 20.0),
            Arguments.of(100.0, 150.0),
            Arguments.of(100.0, 1000.0)
        )
    }
}