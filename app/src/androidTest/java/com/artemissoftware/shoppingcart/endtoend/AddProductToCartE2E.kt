package com.artemissoftware.shoppingcart.endtoend

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.artemissoftware.shoppingcart.InstrumentedProductTestData
import com.artemissoftware.shoppingcart.MainActivity
import com.artemissoftware.shoppingcart.presentation.addproduct.AddProductScreenRobot
import com.artemissoftware.shoppingcart.presentation.cart.CartScreenRobot
import com.artemissoftware.shoppingcart.presentation.details.DetailsScreenRobot
import com.artemissoftware.shoppingcart.presentation.searchproduct.SearchProductRobot
import com.artemissoftware.shoppingcart.test.ShoppingCartAndroidTest
import com.artemissoftware.shoppingcart.test.dispatcher.MockServerDispatcher
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AddProductToCartE2E: ShoppingCartAndroidTest() {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun add_product_to_cart() {
        mockServer.dispatcher = MockServerDispatcher().successDispatcher()

        val cartScreenRobot = CartScreenRobot(composeRule)
        val searchProductRobot = SearchProductRobot(composeRule)

        cartScreenRobot
            .navigateToSearchProduct()

        searchProductRobot
            .searchProduct("egg")
            .selectProduct()

        AddProductScreenRobot(composeRule)
            .assertProductIsDisplayed()
            .addProduct()

        searchProductRobot
            .returnToCart()

        cartScreenRobot
            .assertCartListIsDisplayed()
            .navigateToProductDetail()

        DetailsScreenRobot(composeRule)
            .assertProductIsDisplayed(product = InstrumentedProductTestData.productV1)
    }
}