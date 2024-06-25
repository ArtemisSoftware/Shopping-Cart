package com.artemissoftware.shoppingcart.presentation.cart

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.artemissoftware.shoppingcart.InstrumentedProductTestData.productEntity
import com.artemissoftware.shoppingcart.InstrumentedProductTestData.product_Entity
import com.artemissoftware.shoppingcart.test.ShoppingCartAndroidTest
import com.artemissoftware.shoppingcart.test.TestActivity
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class CartScreenTest: ShoppingCartAndroidTest(){

    @get:Rule
    val composeRule = createAndroidComposeRule<TestActivity>()

    @Test
    fun load_cart_check_products_and_total_are_correct() = runBlocking<Unit>{

        val total = setCartProducts()

        composeRule.setContent {
            CartScreen(
                navigateToDetail = {},
                navigateToSearchProduct = {}
            )
        }

        composeRule.awaitIdle()

        CartScreenRobot(composeRule)
            .assertCartListIsDisplayed()
            .assertCartTotalIsDisplayed(total = total)
    }

    @Test
    fun load_empty_cart() {

        composeRule.setContent {
            CartScreen(
                navigateToDetail = {},
                navigateToSearchProduct = {}
            )
        }

        CartScreenRobot(composeRule)
            .assertEmptyCartListIsDisplayed()
    }

    private suspend fun setCartProducts(): Double{
        db.getProductDao().insert(product_Entity)
        return db.getProductDao().getTotalPrice().first()
    }
}