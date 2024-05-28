package com.artemissoftware.shoppingcart.presentation.addproduct

import androidx.lifecycle.SavedStateHandle
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import com.artemissoftware.shoppingcart.ProductTestData
import com.artemissoftware.shoppingcart.domain.usecases.GetProductUseCase
import com.artemissoftware.shoppingcart.domain.usecases.SaveProductUseCase
import com.artemissoftware.shoppingcart.fakes.FakeCartRepository
import com.artemissoftware.shoppingcart.presentation.navigation.NavArguments
import com.artemissoftware.shoppingcart.util.MainCoroutineExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MainCoroutineExtension::class)
internal class AddProductViewModelTest{

    private lateinit var cartRepository: FakeCartRepository

    private lateinit var getProductUseCase: GetProductUseCase
    private lateinit var saveProductUseCase: SaveProductUseCase

    private lateinit var viewModel: AddProductViewModel

    private val productId = 1
    private val product = ProductTestData.getProduct(id = productId)

    @BeforeEach
    fun setUp() {
        cartRepository = FakeCartRepository(productList = listOf(product))
        getProductUseCase = GetProductUseCase(cartRepository = cartRepository)
        saveProductUseCase = SaveProductUseCase(cartRepository = cartRepository)

        viewModel = AddProductViewModel(
            getProductUseCase = getProductUseCase,
            saveProductUseCase = saveProductUseCase,
            savedStateHandle = SavedStateHandle(
                initialState = mapOf(
                    NavArguments.PRODUCT_ID to productId
                )
            )
        )
    }

    @Test
    fun `On init viewmodel, check initial product`() = runTest {
        assertThat(viewModel.state.value.product)
            .isEqualTo(product)
    }

    @Test
    fun `Add product quantity, check new state`() = runTest  {

        val currentProduct = product.copy(quantity = product.quantity + 1)

        viewModel.onTriggerEvent(AddProductEvent.AddQuantity)

        assertThat(viewModel.state.value.product)
            .isEqualTo(currentProduct)
    }

    @Test
    fun `Remove product quantity when quantity is zero, check new state`() = runTest  {

        val currentProduct = product.copy(quantity = product.quantity - 1)

        viewModel.onTriggerEvent(AddProductEvent.RemoveQuantity)

        assertThat(viewModel.state.value.product)
            .isEqualTo(currentProduct)
    }

    @Test
    fun `Remove product quantity when quantity is zero, check state is not updated`() = runTest  {

        val currentProduct = product.copy(quantity = 0)
        for (i in 1..(product.quantity + 1)) {
            viewModel.onTriggerEvent(AddProductEvent.RemoveQuantity)
        }

        assertThat(viewModel.state.value.product)
            .isEqualTo(currentProduct)
    }
}