package com.artemissoftware.shoppingcart.test

import org.junit.Rule
import androidx.compose.ui.test.junit4.createComposeRule

abstract class ShoppingCartComposableTest {

    @get:Rule
    val composeRule = createComposeRule()
}