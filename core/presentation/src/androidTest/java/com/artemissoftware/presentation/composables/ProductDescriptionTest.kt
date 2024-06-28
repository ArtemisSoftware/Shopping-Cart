package com.artemissoftware.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.artemissoftware.presentation.InstrumentedTestData
import com.artemissoftware.ui.theme.ShoppingCartTheme
import org.junit.Rule
import org.junit.Test

class ProductDescriptionTest{

    @get:Rule
    val composeRule = createComposeRule()
    @Test
    fun `Test product description ui`(){

        composeRule.setContent {
            ShoppingCartTheme {
                ProductDescription(
                    modifier = Modifier.fillMaxWidth(),
                    product = InstrumentedTestData.product
                )
            }
        }

        composeRule
            .onNodeWithTag(TestTags.PRODUCT_DESCRIPTION_CONTENT)
            .assertIsDisplayed()

        with(InstrumentedTestData.product){
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