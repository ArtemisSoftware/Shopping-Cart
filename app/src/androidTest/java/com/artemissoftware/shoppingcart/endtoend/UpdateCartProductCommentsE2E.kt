package com.artemissoftware.shoppingcart.endtoend

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.artemissoftware.shoppingcart.InstrumentedProductTestData
import com.artemissoftware.shoppingcart.InstrumentedProductTestData.commentary
import com.artemissoftware.shoppingcart.MainActivity
import com.artemissoftware.shoppingcart.presentation.addproduct.AddProductScreenRobot
import com.artemissoftware.shoppingcart.presentation.cart.CartScreenRobot
import com.artemissoftware.shoppingcart.presentation.details.DetailsScreenRobot
import com.artemissoftware.shoppingcart.presentation.searchproduct.SearchProductRobot
import com.artemissoftware.shoppingcart.test.ShoppingCartAndroidTest
import com.artemissoftware.shoppingcart.test.dispatcher.MockServerDispatcher
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class UpdateCartProductCommentsE2E: ShoppingCartAndroidTest() {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun update_cart_product_commentary() = runBlocking<Unit> {
        setCartProducts()

        val cartScreenRobot = CartScreenRobot(composeRule)

        cartScreenRobot
            .assertCartListIsDisplayed()
            .navigateToProductDetail()

        DetailsScreenRobot(composeRule)
            .assertProductIsDisplayed(product = InstrumentedProductTestData.productV1.copy(comments = commentary))
            .updateComments("Super duper comments")
            .saveDetail()

        cartScreenRobot
            .assertCartListIsDisplayed()
            .assertProductIsDisplayed(0, "Super duper comments")
    }

    private suspend fun setCartProducts(): Double{
        db.getProductDao().insert(InstrumentedProductTestData.product_Entity)
        return db.getProductDao().getTotalPrice().first()
    }
}