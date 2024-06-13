package com.artemissoftware.shoppingcart.presentation.searchproduct

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule

class SearchProductRobot<T : ComponentActivity>(
    private val composeRule: AndroidComposeTestRule<ActivityScenarioRule<T>, T>
) {
    fun searchProduct(query: String): SearchProductRobot<T> {
        composeRule
            .onNodeWithTag(TestTags.SEARCH_PRODUCT_SEARCH_BAR, useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()
            .performTextInput(query)

        composeRule
            .onNodeWithTag(TestTags.SEARCH_PRODUCT_SEARCH_ICON, useUnmergedTree = true)
            .performClick()

        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun assertProducts(): SearchProductRobot<T> {

        composeRule
            .waitUntilExactlyOneExists(hasTestTag(TestTags.searchProductItemTag(0)))

        composeRule
            .onNodeWithTag(TestTags.searchProductItemTag(0), useUnmergedTree = true)
            .assertIsDisplayed()

        return this
    }

    @OptIn(ExperimentalTestApi::class)
    fun assertError(): SearchProductRobot<T> {

        composeRule
            .waitUntilExactlyOneExists(hasTestTag(TestTags.SEARCH_PRODUCT_ERROR))

        composeRule
            .onNodeWithTag(TestTags.SEARCH_PRODUCT_ERROR, useUnmergedTree = true)
            .assertIsDisplayed()

        return this
    }
}