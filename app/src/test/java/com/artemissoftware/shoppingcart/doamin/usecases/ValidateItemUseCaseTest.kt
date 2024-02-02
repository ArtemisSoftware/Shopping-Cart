package com.artemissoftware.shoppingcart.doamin.usecases

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.artemissoftware.shoppingcart.domain.usecases.ValidateItemUseCase
import com.artemissoftware.shoppingcart.domain.usecases.ValidateItemUseCase.Companion.FIELDS_NOT_EMPTY
import com.artemissoftware.shoppingcart.domain.usecases.ValidateItemUseCase.Companion.INVALID_AMOUNT
import com.artemissoftware.shoppingcart.domain.usecases.ValidateItemUseCase.Companion.NAME_MAX_LENGTH_EXCEEDED
import com.artemissoftware.shoppingcart.domain.usecases.ValidateItemUseCase.Companion.PRICE_MAX_LENGTH_EXCEEDED
import com.artemissoftware.shoppingcart.util.Resource
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ValidateItemUseCaseTest {

    lateinit var validateItemUseCase: ValidateItemUseCase

    @BeforeEach
    fun setUp() {
        validateItemUseCase = ValidateItemUseCase()
    }

    @Test
    fun `Validate shopping item with valid name amount and price should return success`() {
        val resource = validateItemUseCase(name = "Product", amount = "3", price = "21")
        assertThat(resource).isInstanceOf(Resource.Success::class.java)
    }

    @Test
    fun `Validate shopping item without name amount and price should return error`() {
        val resource = validateItemUseCase(name = "", amount = "", price = "")
        assertThat(resource).isInstanceOf(Resource.Error::class.java)
        assertThat((resource as Resource.Error).msg).isEqualTo(FIELDS_NOT_EMPTY)
    }

    @Test
    fun `Validate shopping item with long name should return error`() {
        val resource = validateItemUseCase(name = "This a product with a really long name", amount = "1", price = "2")
        assertThat(resource).isInstanceOf(Resource.Error::class.java)
        assertThat((resource as Resource.Error).msg).isEqualTo(NAME_MAX_LENGTH_EXCEEDED)
    }

    @Test
    fun `Validate shopping item with long price should return error`() {
        val resource = validateItemUseCase(name = "Product", amount = "1", price = "200 000 000 000 000")
        assertThat(resource).isInstanceOf(Resource.Error::class.java)
        assertThat((resource as Resource.Error).msg).isEqualTo(PRICE_MAX_LENGTH_EXCEEDED)
    }

    @Test
    fun `Validate shopping item with invalid amount should return error`() {
        val resource = validateItemUseCase(name = "Product", amount = "A", price = "20")
        assertThat(resource).isInstanceOf(Resource.Error::class.java)
        assertThat((resource as Resource.Error).msg).isEqualTo(INVALID_AMOUNT)
    }
}
