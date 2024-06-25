package com.artemissoftware.shoppingcart.presentation.details.composables

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.artemissoftware.shoppingcart.InstrumentedProductTestData
import com.artemissoftware.shoppingcart.presentation.details.TestTags
import com.artemissoftware.shoppingcart.test.ShoppingCartComposableTest
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme
import org.junit.Assert.assertTrue
import org.junit.Test

class ProductCommentsTest: ShoppingCartComposableTest(){

    @Test
    fun `Test product comments ui`(){

        var hasClickedOnSaveButton = false
        val newComment = "the comment"

        composeRule.setContent {
            ShoppingCartTheme {

                ProductComments(
                    product = InstrumentedProductTestData.product,
                    onSave = {_, _ ->
                        hasClickedOnSaveButton = true
                    },
                )
            }
        }

        composeRule
            .onNodeWithTag(TestTags.PRODUCT_COMMENTS_CONTENT)
            .assertIsDisplayed()

        with(InstrumentedProductTestData.product){
            composeRule
                .onNodeWithTag(TestTags.PRODUCT_COMMENTS_DESCRIPTION)
                .assertIsDisplayed()
                .assertTextEquals(description)

            val comments = composeRule
                .onNodeWithTag(TestTags.PRODUCT_COMMENTS_COMMENT)

            comments
                .assertIsDisplayed()
                .assertTextEquals(this.comments)

            comments
                .performTextInput(newComment)

            comments
                .assertTextEquals(newComment)

            composeRule
                .onNodeWithTag(TestTags.PRODUCT_COMMENTS_SAVE_BUTTON)
                .assertIsDisplayed()
                .performClick()
        }

        assertTrue(hasClickedOnSaveButton)
    }
}