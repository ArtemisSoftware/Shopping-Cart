package com.artemissoftware.shoppingcart.presentation.addproduct

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.artemissoftware.shoppingcart.domain.models.Product
import com.artemissoftware.shoppingcart.presentation.addproduct.TestTags.PRODUCT_DETAIL_BUY_BUTTON
import com.artemissoftware.shoppingcart.presentation.addproduct.TestTags.PRODUCT_DETAIL_DESCRIPTION
import com.artemissoftware.shoppingcart.presentation.addproduct.TestTags.PRODUCT_DETAIL_ERROR
import com.artemissoftware.shoppingcart.presentation.composables.TestTags.PRODUCT_DESCRIPTION_CONTENT
import com.artemissoftware.shoppingcart.presentation.composables.TestTags.PRODUCT_DESCRIPTION_TITLE

class AddProductScreenRobot<T : ComponentActivity> (
    private val composeRule: AndroidComposeTestRule<ActivityScenarioRule<T>, T>
) {
    @OptIn(ExperimentalTestApi::class)
    fun assertProductIsDisplayed(product: Product): AddProductScreenRobot<T> {

        composeRule
            .waitUntilExactlyOneExists(hasTestTag(PRODUCT_DESCRIPTION_CONTENT))

        composeRule
            .onNodeWithTag(PRODUCT_DESCRIPTION_CONTENT)
            .assertIsDisplayed()

        with(product){
            composeRule
                .onNodeWithTag(PRODUCT_DESCRIPTION_TITLE)
                .assertIsDisplayed()
                .assertTextEquals(title)

            composeRule
                .onNodeWithTag(PRODUCT_DETAIL_DESCRIPTION)
                .assertIsDisplayed()
                .assertTextEquals(description)
        }
        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun assertProductIsDisplayed(): AddProductScreenRobot<T> {

        composeRule
            .waitUntilExactlyOneExists(hasTestTag(PRODUCT_DESCRIPTION_CONTENT))
        composeRule
            .onNodeWithTag(PRODUCT_DESCRIPTION_CONTENT)
            .assertIsDisplayed()

        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun addProduct(): AddProductScreenRobot<T> {

        composeRule
            .waitUntilExactlyOneExists(hasTestTag(PRODUCT_DESCRIPTION_CONTENT))

        composeRule
            .onNodeWithTag(PRODUCT_DETAIL_BUY_BUTTON)
            .assertIsDisplayed()
            .performClick()

        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun assertError(): AddProductScreenRobot<T> {

        composeRule
            .waitUntilExactlyOneExists(hasTestTag(PRODUCT_DETAIL_ERROR))

        composeRule
            .onNodeWithTag(PRODUCT_DETAIL_ERROR, useUnmergedTree = true)
            .assertIsDisplayed()

        return this
    }
}