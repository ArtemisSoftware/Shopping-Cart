package com.artemissoftware.shoppingcart.presentation.details

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.artemissoftware.shoppingcart.InstrumentedProductTestData
import com.artemissoftware.shoppingcart.InstrumentedProductTestData.commentary
import com.artemissoftware.shoppingcart.presentation.navigation.NavArguments
import com.artemissoftware.shoppingcart.test.ShoppingCartAndroidTest
import com.artemissoftware.shoppingcart.test.TestActivity
import com.artemissoftware.shoppingcart.test.dispatcher.MockServerDispatcher
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class DetailsScreenTest: ShoppingCartAndroidTest() {

    @get:Rule
    val composeRule = createAndroidComposeRule<TestActivity>()

    @Test
    fun load_existing_product_from_data_base_check_data() = runBlocking<Unit> {

        setProductInDataBase()

        composeRule.setContent {
            DetailsScreen(
                onPopBackStack = {},
            )
        }

        composeRule.awaitIdle()

        DetailsScreenRobot(composeRule)
            .assertProductIsDisplayed(product = InstrumentedProductTestData.productV1.copy(comments = commentary))

    }

    @Test
    fun load_product_from_api_check_data() {

        mockServer.dispatcher = MockServerDispatcher().successDispatcher()
        setProductId(InstrumentedProductTestData.product_Entity.id)

        composeRule.setContent {
            DetailsScreen(
                onPopBackStack = {},
            )
        }

        DetailsScreenRobot(composeRule)
            .assertProductIsDisplayed(product = InstrumentedProductTestData.productV1)
    }

    private suspend fun setProductInDataBase() = with(InstrumentedProductTestData.productEntity){
        composeRule.activity.intent.putExtra(NavArguments.PRODUCT_ID, id)
        db.getProductDao().insert(this)
    }

    private fun setProductId(id: Int) {
        composeRule.activity.intent.putExtra(NavArguments.PRODUCT_ID, id)
    }
}