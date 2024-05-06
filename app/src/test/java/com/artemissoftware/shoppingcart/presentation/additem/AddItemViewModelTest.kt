package com.artemissoftware.shoppingcart.presentation.additem

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.shoppingcart.MainCoroutineRule
import com.artemissoftware.shoppingcart.data.repositories.FakeShoppingCartRepository
import com.artemissoftware.shoppingcart.domain.usecases.InsertShoppingItemUseCase
import com.artemissoftware.shoppingcart.domain.usecases.ValidateItemUseCase
import com.artemissoftware.shoppingcart.util.constants.ValidationConstants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AddItemViewModelTest {

    private lateinit var viewModel: AddItemViewModel
    private lateinit var validateItemUseCase: ValidateItemUseCase
    private lateinit var insertShoppingItemUseCase: InsertShoppingItemUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @BeforeEach
    fun setUp() {
        validateItemUseCase = ValidateItemUseCase()
        insertShoppingItemUseCase = InsertShoppingItemUseCase(shoppingCartRepository = FakeShoppingCartRepository())
        viewModel = AddItemViewModel(validateItemUseCase = validateItemUseCase, insertShoppingItemUseCase = insertShoppingItemUseCase)
    }

    @Test
    fun `when UpdateName is triggered with non-empty value check if name has new value`() {
        val name = "Universe"
        viewModel.onTriggerEvent(AddItemEvents.UpdateName(name = name))
        assertThat(name).isEqualTo(viewModel.state.value.name)
    }

    @Test
    fun `when UpdatePrice is triggered with non-empty value check if price has new value`() {
        val price = "10"
        viewModel.onTriggerEvent(AddItemEvents.UpdatePrice(price = price))
        assertThat(price).isEqualTo(viewModel.state.value.price)
    }

    @Test
    fun `when UpdateAmount is triggered with non-empty value check if amount has new value`() {
        val amount = "2"
        viewModel.onTriggerEvent(AddItemEvents.UpdateAmount(amount = amount))
        assertThat(amount).isEqualTo(viewModel.state.value.amount)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert shopping item with empty field, returns error`() = runTest {
        viewModel.onTriggerEvent(AddItemEvents.UpdatePrice(price = "3.0"))
        viewModel.onTriggerEvent(AddItemEvents.UpdateAmount(amount = ""))
        viewModel.onTriggerEvent(AddItemEvents.UpdateName(name = "name"))

        viewModel.onTriggerEvent(AddItemEvents.InsertItem)
        advanceUntilIdle()

        assertThat(ValidateItemUseCase.FIELDS_NOT_EMPTY).isEqualTo(viewModel.state.value.error)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert shopping item with too long name, returns error`() = runTest {
        val description = buildString {
            for (i in 1..ValidationConstants.MAX_NAME_LENGTH + 1) {
                append(1)
            }
        }
        viewModel.onTriggerEvent(AddItemEvents.UpdatePrice(price = "3.0"))
        viewModel.onTriggerEvent(AddItemEvents.UpdateAmount(amount = "2"))
        viewModel.onTriggerEvent(AddItemEvents.UpdateName(name = description))

        viewModel.onTriggerEvent(AddItemEvents.InsertItem)
        advanceUntilIdle()

        assertThat(ValidateItemUseCase.NAME_MAX_LENGTH_EXCEEDED).isEqualTo(viewModel.state.value.error)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert shopping item with too long price, returns error`() = runTest {
        val price = buildString {
            for (i in 1..ValidationConstants.MAX_PRICE_LENGTH + 1) {
                append(1)
            }
        }
        viewModel.onTriggerEvent(AddItemEvents.UpdatePrice(price = price))
        viewModel.onTriggerEvent(AddItemEvents.UpdateAmount(amount = "5"))
        viewModel.onTriggerEvent(AddItemEvents.UpdateName(name = "name"))

        viewModel.onTriggerEvent(AddItemEvents.InsertItem)
        advanceUntilIdle()

        assertThat(ValidateItemUseCase.PRICE_MAX_LENGTH_EXCEEDED).isEqualTo(viewModel.state.value.error)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert shopping item with too high amount, returns error`() = runTest {
        viewModel.onTriggerEvent(AddItemEvents.UpdatePrice(price = "3.0"))
        viewModel.onTriggerEvent(AddItemEvents.UpdateAmount(amount = "9999999999999999999"))
        viewModel.onTriggerEvent(AddItemEvents.UpdateName(name = "name"))

        viewModel.onTriggerEvent(AddItemEvents.InsertItem)
        advanceUntilIdle()

        assertThat(ValidateItemUseCase.INVALID_AMOUNT).isEqualTo(viewModel.state.value.error)
    }

/*
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert shopping item with invalid fields does not insert data`() = runTest {
        every { validateItemUseCase(any(), any(), any()) } returns Resource.Error(msg = "Error", null)

        viewModel.onTriggerEvent(AddItemEvents.InsertItem)
        advanceUntilIdle()

        coVerify { validateItemUseCase(any(), any(), any()) }
        coVerify(exactly = 0) { insertShoppingItemUseCase(any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert shopping item with valid fields, inserts data`() = runTest {
        every { validateItemUseCase(any(), any(), any()) } returns Resource.Success()

        viewModel.onTriggerEvent(AddItemEvents.UpdatePrice(price = "1"))
        viewModel.onTriggerEvent(AddItemEvents.UpdateAmount(amount = "1"))
        viewModel.onTriggerEvent(AddItemEvents.UpdateName(name = "ere"))
        viewModel.onTriggerEvent(AddItemEvents.InsertItem)
        advanceUntilIdle()

        coVerify { validateItemUseCase(any(), any(), any()) }
        coVerify(exactly = 1) { insertShoppingItemUseCase(any()) }
    }
    */
}
