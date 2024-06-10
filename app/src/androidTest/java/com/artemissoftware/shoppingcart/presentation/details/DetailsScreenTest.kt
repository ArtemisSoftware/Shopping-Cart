package com.artemissoftware.shoppingcart.presentation.details

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.artemissoftware.shoppingcart.InstrumentedProductTestData
import com.artemissoftware.shoppingcart.InstrumentedProductTestData.commentary
import com.artemissoftware.shoppingcart.InstrumentedProductTestData.productName
import com.artemissoftware.shoppingcart.InstrumentedProductTestData.productPrice
import com.artemissoftware.shoppingcart.InstrumentedProductTestData.tags
import com.artemissoftware.shoppingcart.presentation.navigation.NavArguments
import com.artemissoftware.shoppingcart.test.ShoppingCartAndroidTest
import com.artemissoftware.shoppingcart.test.TestActivity
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

        setProduct()

        composeRule.setContent {
            DetailsScreen(
                onPopBackStack = {},
            )
        }

        composeRule.awaitIdle()

        DetailsScreenRobot(composeRule)
            .assertProductIsDisplayed(
                title = productName.capitalize(),
                price = productPrice,
                description = tags,
                comments = commentary,
            )

    }

    fun load_product_from_api_check_data() = runBlocking<Unit> {
        // TODO: add test
    }

    private suspend fun setProduct() = with(InstrumentedProductTestData.productEntity){
        composeRule.activity.intent.putExtra(NavArguments.PRODUCT_ID, id)
        db.getProductDao().insert(this)
    }
}