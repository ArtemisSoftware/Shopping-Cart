package com.artemissoftware.shoppingcart.presentation.details

import androidx.lifecycle.SavedStateHandle
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import com.artemissoftware.shoppingcart.ProductTestData.getProduct
import com.artemissoftware.shoppingcart.domain.models.SnackBarState
import com.artemissoftware.shoppingcart.domain.usecases.GetProductUseCase
import com.artemissoftware.shoppingcart.domain.usecases.SaveProductUseCase
import com.artemissoftware.shoppingcart.domain.usecases.ValidateDetailUseCase
import com.artemissoftware.shoppingcart.fakes.FakeCartRepository
import com.artemissoftware.shoppingcart.presentation.navigation.NavArguments
import com.artemissoftware.shoppingcart.test.util.extensions.MainCoroutineExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
internal class DetailsViewModelTest{

    private lateinit var cartRepository: FakeCartRepository

    private lateinit var getProductUseCase: GetProductUseCase
    private lateinit var saveProductUseCase: SaveProductUseCase
    private lateinit var validateDetailUseCase: ValidateDetailUseCase

    private lateinit var viewModel: DetailsViewModel

    private val productId = 1
    private val product = getProduct(id = productId)


    @BeforeEach
    fun setUp(){
        cartRepository = FakeCartRepository(productList = listOf(product))

        getProductUseCase = GetProductUseCase(cartRepository = cartRepository)
        saveProductUseCase = SaveProductUseCase(cartRepository = cartRepository)
        validateDetailUseCase = ValidateDetailUseCase()

        viewModel = DetailsViewModel(
            getProductUseCase = getProductUseCase,
            saveProductUseCase = saveProductUseCase,
            validateDetailUseCase = validateDetailUseCase,
            savedStateHandle = SavedStateHandle(
                initialState = mapOf(
                    NavArguments.PRODUCT_ID to productId
                )
            )
        )
    }

    @Test
    fun `On init viewmodel, get a product with success`() = runTest {
        assertThat(viewModel.state.value.product)
            .isEqualTo(product)

        assertThat(viewModel.state.value.isLoading)
            .isFalse()
    }

    @Test
    fun `Save product, error occurs`() = runTest {

        viewModel.onTriggerEvent(DetailsEvent.Save(comment = "", promoCode = ""))

        assertThat(viewModel.state.value.snackBarState)
            .isEqualTo(SnackBarState.Info("Error"))

    }
}