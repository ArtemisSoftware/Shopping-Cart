package com.artemissoftware.shoppingcart.presentation.searchproduct

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.artemissoftware.shoppingcart.ProductTestData
import com.artemissoftware.shoppingcart.domain.error.DataError
import com.artemissoftware.shoppingcart.domain.error.ProductError
import com.artemissoftware.shoppingcart.domain.models.Cart
import com.artemissoftware.shoppingcart.domain.models.SnackBarState
import com.artemissoftware.shoppingcart.domain.usecases.GetCartUseCase
import com.artemissoftware.shoppingcart.domain.usecases.SearchProductsUseCase
import com.artemissoftware.shoppingcart.fakes.FakeCartRepository
import com.artemissoftware.shoppingcart.presentation.cart.CartViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class SearchProductViewModelTest{

    private lateinit var cartRepository: FakeCartRepository
    private lateinit var searchProductsUseCase: SearchProductsUseCase

    private lateinit var viewModel: SearchProductViewModel

    private val products = listOf(ProductTestData.getProduct(id = 1, name = "egg"))

    @BeforeEach
    fun setUp() {
        val testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)

        cartRepository = FakeCartRepository(productList = products)

        searchProductsUseCase = SearchProductsUseCase(cartRepository)

        viewModel = SearchProductViewModel(searchProductsUseCase = searchProductsUseCase)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `On search query update, check state update`() = runTest {

        viewModel.onEventTrigger(SearchProductEvent.UpdateQuery("query"))

        assertThat(viewModel.state.value.searchQuery)
            .isEqualTo("query")
    }

    @Test
    fun `On search product that does not exist, check state update`() = runTest {

        viewModel.onEventTrigger(SearchProductEvent.Search)

        advanceUntilIdle()

        assertThat(viewModel.state.value.snackBarState)
            .isEqualTo(SnackBarState.Info("No products found"))

        assertThat(viewModel.state.value.products)
            .isEmpty()
    }

    @Test
    fun `On search product, api returns error, check state update`() = runTest {
        cartRepository.errorToReturn = DataError.NetworkError.Unknown

        viewModel.onEventTrigger(SearchProductEvent.Search)

        advanceUntilIdle()

        assertThat(viewModel.state.value.snackBarState)
            .isEqualTo(SnackBarState.Info("Error"))
    }

    @Test
    fun `On search product, check state update`() = runTest {
        viewModel.onEventTrigger(SearchProductEvent.UpdateQuery("egg"))
        viewModel.onEventTrigger(SearchProductEvent.Search)

        advanceUntilIdle()

        assertThat(viewModel.state.value.snackBarState)
            .isNull()

        assertThat(viewModel.state.value.snackBarState)
            .isNull()
    }
}