package com.artemissoftware.product.addproduct.composables

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.artemissoftware.product.InstrumentedTestData
import com.artemissoftware.shoppingcart.presentation.addproduct.TestTags
import com.artemissoftware.testing.ShoppingCartComposableTest
import com.artemissoftware.ui.theme.ShoppingCartTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ProductDetailTest: ShoppingCartComposableTest(){

    @Test
    fun `Test product detail ui`(){

        var quantity = InstrumentedTestData.product.quantity
        var hasClickedOnBuyNowButton = false

        composeRule.setContent {
            ShoppingCartTheme {

                ProductDetail(
                    product = InstrumentedTestData.product,
                    onBuyNow = {
                        hasClickedOnBuyNowButton = true
                    },
                    onRemoveQuantity = {
                        --quantity
                    },
                    onAddQuantity = {
                        ++quantity
                    },
                )
            }
        }

        with(InstrumentedTestData.product){
            composeRule
                .onNodeWithTag(TestTags.PRODUCT_DETAIL_CONTENT)
                .assertIsDisplayed()

            composeRule
                .onNodeWithTag(TestTags.PRODUCT_DETAIL_DESCRIPTION)
                .assertIsDisplayed()
                .assertTextEquals(description)

            composeRule
                .onNodeWithTag(TestTags.PRODUCT_DETAIL_QUANTITY)
                .assertIsDisplayed()
                .assertTextEquals(quantity.toString())

            composeRule
                .onNodeWithTag(TestTags.PRODUCT_DETAIL_ADD_BUTTON, useUnmergedTree = true)
                .assertIsDisplayed()
                .performClick()

            assertEquals(quantity, this.quantity + 1)

            composeRule
                .onNodeWithTag(TestTags.PRODUCT_DETAIL_REMOVE_BUTTON, useUnmergedTree = true)
                .assertIsDisplayed()
                .performClick()

            assertEquals(quantity, this.quantity)

            composeRule
                .onNodeWithTag(TestTags.PRODUCT_DETAIL_QUANTITY)
                .assertIsDisplayed()
                .assertTextEquals(quantity.toString())

            composeRule
                .onNodeWithTag(TestTags.PRODUCT_DETAIL_BUY_BUTTON)
                .assertIsDisplayed()
                .performClick()
        }

        assertTrue(hasClickedOnBuyNowButton)
    }
}