package com.artemissoftware.shoppingcart.domain.usecases

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import com.artemissoftware.shoppingcart.domain.Resource
import com.artemissoftware.shoppingcart.domain.error.ValidationError
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class ValidateDetailUseCaseTest{

    private lateinit var validateDetailUseCase: ValidateDetailUseCase

    @BeforeEach
    fun setUp(){
        validateDetailUseCase = ValidateDetailUseCase()
    }

    @Test
    fun `Validate detail with valid comments and valid promocode`() {

        val result = validateDetailUseCase(comments = "comment", promoCode = "123")

        assertThat(actual = result is Resource.Success<Unit>)
            .isTrue()
    }

    @ParameterizedTest(name = "Comment <{0}> and promoCode <{1}> with error <{2}>")
    @MethodSource("commentsAndPromocode")
    fun `Validate detail with comments and promocode, return error`(comments: String, promoCode: String, error: ValidationError) {

        val result = validateDetailUseCase(comments = comments, promoCode = promoCode)

        assertThat(actual = result is Resource.Failure)
            .isTrue()

        assertThat(actual = (result as Resource.Failure).error)
            .isEqualTo(error)
    }

    private companion object {
        @JvmStatic
        fun commentsAndPromocode(): Stream<Arguments> = Stream.of(
            Arguments.of("comment", "", ValidationError.DetailError.EmptyField),
            Arguments.of("", "123", ValidationError.DetailError.EmptyField),
            Arguments.of("", "", ValidationError.DetailError.EmptyField),
            Arguments.of("This comment is too big for comments", "123", ValidationError.DetailError.TooManyCharacters),
            Arguments.of("comment", "promoCode", ValidationError.DetailError.InvalidPromocodeNumber),
        )
    }
}