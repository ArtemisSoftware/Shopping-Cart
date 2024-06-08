package com.artemissoftware.shoppingcart.presentation.cart

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import com.artemissoftware.shoppingcart.InstrumentedProductTestData.productEntity
import com.artemissoftware.shoppingcart.MainActivity
import com.artemissoftware.shoppingcart.presentation.cart.TestTags.CART_LIST
import com.artemissoftware.shoppingcart.presentation.cart.TestTags.CART_TOTAL_BAR
import com.artemissoftware.shoppingcart.presentation.cart.TestTags.TOTAL_VALUE
import com.artemissoftware.shoppingcart.test.ShoppingCartAndroidTest
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CartScreenTest: ShoppingCartAndroidTest(){


    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun load_cart_check_it_has_products_and_total_is_correct() = runBlocking<Unit>{

        db.getProductDao().insert(productEntity)
        val total = productEntity.price * productEntity.amount
        composeRule.awaitIdle()

        val cartList = composeRule
            .onNodeWithTag(CART_LIST)

        cartList
            .assertIsDisplayed()

        cartList
            .onChildAt(0)
            .assertIsDisplayed()

        composeRule
            .onNodeWithTag(CART_TOTAL_BAR)
            .assertIsDisplayed()

        composeRule
            .onNodeWithTag(TOTAL_VALUE)
            .assertIsDisplayed()
            .assertTextEquals(total.toString())
    }
}