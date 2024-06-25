package com.artemissoftware.shoppingcart.presentation.cart.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.artemissoftware.shoppingcart.InstrumentedProductTestData
import com.artemissoftware.shoppingcart.R
import com.artemissoftware.shoppingcart.presentation.cart.TestTags
import com.artemissoftware.shoppingcart.test.ShoppingCartComposableTest
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme
import org.junit.Assert
import org.junit.Test


class ProductCardTest: ShoppingCartComposableTest(){

    @Test
    fun `Test product card ui`(){

        var quantityLabel = ""
        var hasClickedOnCard = false

        composeRule.setContent {
            ShoppingCartTheme {

                quantityLabel = stringResource(id = R.string.qty)

                ProductCard(
                    product = InstrumentedProductTestData.product,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        hasClickedOnCard = true
                    }
                )
            }
        }

        composeRule
            .onNodeWithTag(TestTags.PRODUCT_CARD)
            .assertIsDisplayed()
            .performClick()

        composeRule
            .onNodeWithTag(TestTags.PRODUCT_CARD_CONTENT, useUnmergedTree = true)
            .assertIsDisplayed()

        composeRule
            .onNodeWithTag(TestTags.PRODUCT_CARD_IMAGE, useUnmergedTree = true)
            .assertExists()

        with(InstrumentedProductTestData.product){
            composeRule
                .onNodeWithTag(TestTags.PRODUCT_CARD_TITLE, useUnmergedTree = true)
                .assertIsDisplayed()
                .assertTextEquals(title)

            composeRule
                .onNodeWithTag(TestTags.PRODUCT_CARD_TOTAL, useUnmergedTree = true)
                .assertIsDisplayed()
                .assertTextEquals(totalPrice().toString())

            composeRule
                .onNodeWithTag(TestTags.PRODUCT_CARD_QUANTITY_LABEL, useUnmergedTree = true)
                .assertIsDisplayed()
                .assertTextEquals(quantityLabel)

            composeRule
                .onNodeWithTag(TestTags.PRODUCT_CARD_QUANTITY, useUnmergedTree = true)
                .assertIsDisplayed()
                .assertTextEquals(quantity.toString())

            if(comments.isEmpty()){
                composeRule
                    .onNodeWithTag(TestTags.PRODUCT_CARD_COMMENT, useUnmergedTree = true)
                    .assertIsNotDisplayed()
            } else {
                composeRule
                    .onNodeWithTag(TestTags.PRODUCT_CARD_COMMENT, useUnmergedTree = true)
                    .assertIsDisplayed()
                    .assertTextEquals(comments)
            }

            Assert.assertTrue(hasClickedOnCard)
        }
    }
}