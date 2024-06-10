package com.artemissoftware.shoppingcart.presentation.cart.composables

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import com.artemissoftware.shoppingcart.R
import com.artemissoftware.shoppingcart.presentation.cart.TestTags
import com.artemissoftware.shoppingcart.test.ShoppingCartComposableTest
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme
import org.junit.Test

class TotalBarTest: ShoppingCartComposableTest() {

    @Test
    fun `Test total bar ui`(){

        var description = ""
        val total = "10"

        composeRule.setContent {
            ShoppingCartTheme {

                description = stringResource(id = R.string.total_cost)

                TotalBar(
                    total = total
                )
            }
        }

        composeRule.onNodeWithTag(TestTags.TOTAL_CONTENT)
            .assertIsDisplayed()

        composeRule.onNodeWithTag(TestTags.TOTAL_LABEL)
            .assertIsDisplayed()
            .assertTextEquals(description)

        composeRule.onNodeWithTag(TestTags.TOTAL_VALUE)
            .assertIsDisplayed()
            .assertTextEquals(total)
    }
}