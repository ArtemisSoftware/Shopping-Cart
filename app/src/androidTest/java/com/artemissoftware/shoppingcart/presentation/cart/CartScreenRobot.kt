package com.artemissoftware.shoppingcart.presentation.cart

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import com.artemissoftware.shoppingcart.test.ShoppingCartComposeRule

class CartScreenRobot(
    private val composeRule: ShoppingCartComposeRule
) {

    fun assertCartListIsDisplayed(): CartScreenRobot {
        val cartList = composeRule
            .onNodeWithTag(TestTags.CART_LIST)

        cartList
            .assertIsDisplayed()

        cartList
            .onChildAt(0)
            .assertIsDisplayed()
        return this
    }

    fun assertCartTotalIsDisplayed(total: Double): CartScreenRobot {
        composeRule
            .onNodeWithTag(TestTags.CART_TOTAL_BAR)
            .assertIsDisplayed()

        composeRule
            .onNodeWithTag(TestTags.TOTAL_VALUE)
            .assertIsDisplayed()
            .assertTextEquals(total.toString())
        return this
    }
}