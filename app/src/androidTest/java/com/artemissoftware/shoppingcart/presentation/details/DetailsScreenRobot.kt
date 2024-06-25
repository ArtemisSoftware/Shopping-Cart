package com.artemissoftware.shoppingcart.presentation.details

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.artemissoftware.shoppingcart.domain.models.Product
import com.artemissoftware.shoppingcart.presentation.details.TestTags.PRODUCT_DETAIL_ERROR
import com.artemissoftware.shoppingcart.presentation.composables.TestTags as ComposableTestTags

class DetailsScreenRobot<T : ComponentActivity>(
    private val composeRule: AndroidComposeTestRule<ActivityScenarioRule<T>, T>
) {
    @OptIn(ExperimentalTestApi::class)
    fun assertProductIsDisplayed(
        product: Product,
    ): DetailsScreenRobot<T> {

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

    fun updateComments(comments: String): DetailsScreenRobot<T>  {

        composeRule
            .onNodeWithTag(TestTags.PRODUCT_COMMENTS_COMMENT)
            .assertIsDisplayed()
            .performTextClearance()

        composeRule
            .onNodeWithTag(TestTags.PRODUCT_COMMENTS_COMMENT)
            .performTextInput(comments)

        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun assertError(): DetailsScreenRobot<T> {

        composeRule
            .waitUntilExactlyOneExists(hasTestTag(PRODUCT_DETAIL_ERROR))

        composeRule
            .onNodeWithTag(PRODUCT_DETAIL_ERROR, useUnmergedTree = true)
            .assertIsDisplayed()

        return this
    }

    fun saveDetail(): DetailsScreenRobot<T>  {
        composeRule
            .onNodeWithTag(TestTags.PRODUCT_COMMENTS_SAVE_BUTTON)
            .assertIsDisplayed()
            .performClick()

        return this
    }
}