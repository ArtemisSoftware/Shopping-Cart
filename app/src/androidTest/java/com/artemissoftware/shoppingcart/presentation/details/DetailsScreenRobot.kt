package com.artemissoftware.shoppingcart.presentation.details

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.artemissoftware.shoppingcart.domain.models.Product
import com.artemissoftware.shoppingcart.test.ShoppingCartComposeRule
import com.artemissoftware.shoppingcart.presentation.composables.TestTags as ComposableTestTags

class DetailsScreenRobot(
    private val composeRule: ShoppingCartComposeRule
) {
    @OptIn(ExperimentalTestApi::class)
    fun assertProductIsDisplayed(
        product: Product,
    ): DetailsScreenRobot {

        composeRule
            .waitUntilExactlyOneExists(hasTestTag(ComposableTestTags.PRODUCT_DESCRIPTION_TITLE))

        composeRule
            .onNodeWithTag(ComposableTestTags.PRODUCT_DESCRIPTION_CONTENT)
            .assertIsDisplayed()

        with(product){
            composeRule
                .onNodeWithTag(ComposableTestTags.PRODUCT_DESCRIPTION_TITLE)
                .assertIsDisplayed()
                .assertTextEquals(title)
// TODO: refactor price
//            composeRule
//                .onNodeWithTag(ComposableTestTags.PRODUCT_DESCRIPTION_PRICE)
//                .assertIsDisplayed()
//                .assertTextEquals(price.toString())

            composeRule
                .onNodeWithTag(TestTags.PRODUCT_COMMENTS_CONTENT)
                .assertIsDisplayed()

            composeRule
                .onNodeWithTag(TestTags.PRODUCT_COMMENTS_DESCRIPTION)
                .assertIsDisplayed()
                .assertTextEquals(description)

            composeRule
                .onNodeWithTag(TestTags.PRODUCT_COMMENTS_COMMENT)
                .assertIsDisplayed()
                .assertTextEquals(comments)
        }

        return this
    }

    fun updateComments(comments: String): DetailsScreenRobot {

        composeRule
            .onNodeWithTag(TestTags.PRODUCT_COMMENTS_COMMENT)
            .assertIsDisplayed()
            .performTextInput(comments)

        return this
    }

    fun saveDetail(): DetailsScreenRobot {
        composeRule
            .onNodeWithTag(TestTags.PRODUCT_COMMENTS_SAVE_BUTTON)
            .assertIsDisplayed()
            .performClick()

        return this
    }
}