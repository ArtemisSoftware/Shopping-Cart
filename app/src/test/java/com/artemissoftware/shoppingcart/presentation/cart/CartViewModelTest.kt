package com.artemissoftware.shoppingcart.presentation.cart

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.shoppingcart.ProductTestData
import com.artemissoftware.shoppingcart.domain.models.Cart
import com.artemissoftware.shoppingcart.domain.usecases.GetCartUseCase
import com.artemissoftware.shoppingcart.fakes.FakeCartRepository
import com.artemissoftware.shoppingcart.util.MainCoroutineExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
internal class CartViewModelTest {

    private lateinit var cartRepository: FakeCartRepository

    private lateinit var getCartUseCase: GetCartUseCase

    private lateinit var viewModel: CartViewModel

    private val products = listOf(ProductTestData.getProduct())

    @BeforeEach
    fun setUp() {
        cartRepository = FakeCartRepository(productList = products)

        getCartUseCase = GetCartUseCase(cartRepository)

        viewModel = CartViewModel(getCartUseCase = getCartUseCase)
    }

    @Test
    fun `On init viewmodel, check cart emissions`() = runTest {

        val cart = Cart(
            products = products,
            total = products.sumOf { it.totalPrice() }
        )

        viewModel.state.test {
            val emission1 = awaitItem()
            assertThat(emission1.cart).isEqualTo(Cart())

            val emission2 = awaitItem()
            assertThat(emission2.cart).isEqualTo(cart)
        }
    }
}