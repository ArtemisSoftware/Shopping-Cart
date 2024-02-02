package com.artemissoftware.shoppingcart.domain.usecases

import com.artemissoftware.shoppingcart.util.Resource
import com.artemissoftware.shoppingcart.util.constants.ValidationConstants

class ValidateItemUseCase constructor() {

    operator fun invoke(name: String, amount: String, price: String): Resource<Unit> {
        if (name.isEmpty() || amount.isEmpty() || price.isEmpty()) {
            return Resource.Error(msg = FIELDS_NOT_EMPTY, null)
        }
        if (name.length > ValidationConstants.MAX_NAME_LENGTH) {
            return Resource.Error(NAME_MAX_LENGTH_EXCEEDED, null)
        }
        if (price.length > ValidationConstants.MAX_PRICE_LENGTH) {
            return Resource.Error(PRICE_MAX_LENGTH_EXCEEDED, null)
        }
        try {
            amount.toInt()
        } catch (e: NumberFormatException) {
            return Resource.Error(INVALID_AMOUNT, null)
        }

        return Resource.Success()
    }

    companion object {
        const val FIELDS_NOT_EMPTY = "The fields must not be empty"
        const val NAME_MAX_LENGTH_EXCEEDED = "The name of the item must not exceed ${ValidationConstants.MAX_NAME_LENGTH} characters"
        const val PRICE_MAX_LENGTH_EXCEEDED = "The price of the item must not exceed ${ValidationConstants.MAX_PRICE_LENGTH} characters"
        const val INVALID_AMOUNT = "Please enter a valid amount"
    }
}
