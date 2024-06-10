package com.artemissoftware.shoppingcart.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import com.artemissoftware.shoppingcart.InstrumentedProductTestData
import com.artemissoftware.shoppingcart.test.ShoppingCartComposableTest
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme
import org.junit.Test

class ProductDescriptionTest: ShoppingCartComposableTest(){

    @Test
    fun `Test product description ui`(){

        composeRule.setContent {
            ShoppingCartTheme {
                ProductDescription(
                    modifier = Modifier.fillMaxWidth(),
                    product = InstrumentedProductTestData.product
                )
            }
        }

        composeRule
            .onNodeWithTag(TestTags.PRODUCT_DESCRIPTION_CONTENT)
            .assertIsDisplayed()

        with(InstrumentedProductTestData.product){
            composeRule
                .onNodeWithTag(TestTags.PRODUCT_DESCRIPTION_TITLE)
                .assertIsDisplayed()
                .assertTextEquals(title)

            composeRule
                .onNodeWithTag(TestTags.PRODUCT_DESCRIPTION_PRICE)
                .assertIsDisplayed()
                .assertTextEquals(price.toString())
        }
    }
}