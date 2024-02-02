package com.artemissoftware.shoppingcart.domain

import com.artemissoftware.shoppingcart.util.Resource
import com.artemissoftware.shoppingcart.util.constants.ValidationConstants

class ValidateItemUseCase constructor() {

    operator fun invoke(name: String, amount: String, price: String): Resource<Unit> {
        if (name.isEmpty() || amount.isEmpty() || price.isEmpty()) {
            return Resource.Error(msg = "The fields must not be empty", null)
        }
        if (name.length > ValidationConstants.MAX_NAME_LENGTH) {
            return Resource.Error("The name of the item must not exceed ${ValidationConstants.MAX_NAME_LENGTH} characters", null)
        }
        if (price.length > ValidationConstants.MAX_PRICE_LENGTH) {
            return Resource.Error("The price of the item must not exceed ${ValidationConstants.MAX_PRICE_LENGTH} characters", null)
        }
        try {
            amount.toInt()
        } catch (e: NumberFormatException) {
            return Resource.Error("Please enter a valid amount", null)
        }

        return Resource.Success()
    }
}
