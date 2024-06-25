package com.artemissoftware.shoppingcart.presentation.addproduct

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import assertk.assertThat
import assertk.assertions.isTrue
import com.artemissoftware.shoppingcart.InstrumentedProductTestData
import com.artemissoftware.shoppingcart.presentation.navigation.NavArguments
import com.artemissoftware.shoppingcart.test.ShoppingCartAndroidTest
import com.artemissoftware.shoppingcart.test.TestActivity
import com.artemissoftware.shoppingcart.test.dispatcher.MockServerDispatcher
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class AddProductScreenTest: ShoppingCartAndroidTest() {

    @get:Rule
    val composeRule = createAndroidComposeRule<TestActivity>()

    @Test
    fun load_product_from_api_and_add_it(){

        mockServer.dispatcher = MockServerDispatcher().successDispatcher()
        composeRule.activity.intent.putExtra(NavArguments.PRODUCT_ID, InstrumentedProductTestData.productV1.id)
        var productAdded = false


        composeRule.setContent {
            AddProductScreen(
                onPopBackStack = { productAdded = true },
                navigateToSearchProduct = {}
            )
        }

        AddProductScreenRobot(composeRule)
            .assertProductIsDisplayed(product = InstrumentedProductTestData.productV1)
            .addProduct()

        assertThat(productAdded)
            .isTrue()
    }

    @Test
    fun load_product_from_api_returns_error()  = runBlocking<Unit>{

        mockServer.dispatcher = MockServerDispatcher().errorDispatcher()
        composeRule.activity.intent.putExtra(NavArguments.PRODUCT_ID, 10)

        composeRule.setContent {
            AddProductScreen(
                onPopBackStack = {},
                navigateToSearchProduct = {}
            )
        }

        AddProductScreenRobot(composeRule)
            .assertError()
    }
}