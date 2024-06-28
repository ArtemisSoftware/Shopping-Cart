package com.artemissoftware.domain.usecases

import com.artemissoftware.domain.Resource
import com.artemissoftware.domain.error.ValidationError
import javax.inject.Inject

class ValidateDetailUseCase @Inject constructor() {

    operator fun invoke(comments: String, promoCode: String): Resource<Unit> {
        if (comments.isEmpty() || promoCode.isEmpty()) {
            return Resource.Failure(ValidationError.DetailError.EmptyField)
        }
        if (comments.length > MAX_NAME_LENGTH) {
            return Resource.Failure(ValidationError.DetailError.TooManyCharacters)
        }

        try {
            promoCode.toInt()
        } catch (e: NumberFormatException) {
            return Resource.Failure(ValidationError.DetailError.InvalidPromocodeNumber)
        }

        return Resource.Success(Unit)
    }

    private companion object{
        const val MAX_NAME_LENGTH = 30
    }
}