package com.artemissoftware.shoppingcart.presentation.cart

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.artemissoftware.shoppingcart.InstrumentedProductTestData.productEntity
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
    fun load_cart_check_it_has_products_and_total_is_correct() = runBlocking<Unit>{

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

    private suspend fun setCartProducts(): Double{
        db.getProductDao().insert(productEntity)
        return db.getProductDao().getTotalPrice().first()
    }
}