package com.artemissoftware.shoppingcart.domain.usecases

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import assertk.assertions.isTrue
import com.artemissoftware.shoppingcart.ProductTestData
import com.artemissoftware.shoppingcart.domain.Resource
import com.artemissoftware.shoppingcart.domain.models.Cart
import com.artemissoftware.shoppingcart.fakes.FakeCartRepository
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GetCartUseCaseTest{

    private lateinit var cartRepository: FakeCartRepository
    private lateinit var getCartUseCase: GetCartUseCase

    private val productsOnCart = ProductTestData.getProduct()

    @BeforeEach
    fun setUp(){
        cartRepository = FakeCartRepository()

        getCartUseCase = GetCartUseCase(cartRepository = cartRepository)
    }

    @Test
    fun `Validate detail with valid comments and valid promocode`() = runBlocking {

        getCartUseCase().test {

            var emission = awaitItem()

            with(emission){
                assertThat(actual = total)
                    .isEqualTo(0.0)
                assertThat(actual = products)
                    .isEmpty()
            }

            cartRepository.saveProduct(productsOnCart)

            emission = awaitItem()

            with(emission){
                assertThat(actual = total)
                    .isEqualTo(0.0)
                assertThat(actual = products)
                    .isEqualTo(listOf(productsOnCart))
            }

            emission = awaitItem()
            with(emission) {
                assertThat(actual = total)
                    .isEqualTo(productsOnCart.totalPrice())
                assertThat(actual = products)
                    .isEqualTo(listOf(productsOnCart))
            }
        }
    }
}