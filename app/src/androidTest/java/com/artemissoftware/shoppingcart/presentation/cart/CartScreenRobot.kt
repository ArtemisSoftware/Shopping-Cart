package com.artemissoftware.shoppingcart.presentation.cart

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule

class CartScreenRobot<T : ComponentActivity>(
    private val composeRule: AndroidComposeTestRule<ActivityScenarioRule<T>, T>
) {

    fun assertCartListIsDisplayed(): CartScreenRobot<T> {
        val cartList = composeRule
            .onNodeWithTag(TestTags.CART_LIST)

        cartList
            .assertIsDisplayed()

        cartList
            .onChildAt(0)
            .assertIsDisplayed()
        return this
    }

    fun assertCartTotalIsDisplayed(total: Double): CartScreenRobot<T> {
        composeRule
            .onNodeWithTag(TestTags.CART_TOTAL_BAR)
            .assertIsDisplayed()

        composeRule
            .onNodeWithTag(TestTags.TOTAL_VALUE)
            .assertIsDisplayed()
            .assertTextEquals(total.toString())
        return this
    }

    fun navigateToSearchProduct(): CartScreenRobot<T> {
        composeRule
            .onNodeWithTag(TestTags.CART_ADD_BUTTON)
            .assertIsDisplayed()
            .performClick()
        return this
    }
}