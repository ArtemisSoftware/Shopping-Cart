package com.artemissoftware.shoppingcart.presentation.searchproduct

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.artemissoftware.shoppingcart.test.ShoppingCartAndroidTest
import com.artemissoftware.shoppingcart.test.TestActivity
import com.artemissoftware.shoppingcart.test.dispatcher.MockServerDispatcher
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SearchProductScreenTest: ShoppingCartAndroidTest() {

    @get:Rule
    val composeRule = createAndroidComposeRule<TestActivity>()

    @Test
    fun search_product_by_query() {

        mockServer.dispatcher = MockServerDispatcher().successDispatcher()

        composeRule.setContent {
            SearchProductScreen(
                onPopBackStack = {},
                navigateToAddProduct = {},
            )
        }

        SearchProductRobot(composeRule)
            .searchProduct(query = "egg")
            .assertProducts()
    }

    @Test
    fun search_product_by_query_returns_error() = runBlocking<Unit> {

        mockServer.dispatcher = MockServerDispatcher().errorDispatcher()

        composeRule.setContent {
            SearchProductScreen(
                onPopBackStack = {},
                navigateToAddProduct = {},
            )
        }

        SearchProductRobot(composeRule)
            .searchProduct(query = "egg")
            .assertError()
    }
}