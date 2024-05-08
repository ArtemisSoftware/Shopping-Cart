package com.artemissoftware.shoppingcart.presentation.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.shoppingcart.presentation.cart.composables.ProductCard
import com.artemissoftware.shoppingcart.presentation.cart.composables.TotalBar
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme

@Composable
fun CartScreen(
    navigateToAddProduct: () -> Unit,
) {

    CartScreenContent(
        navigateToAddProduct = navigateToAddProduct
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CartScreenContent(
    navigateToAddProduct: () -> Unit,
) {

    Scaffold(
        bottomBar = {
            TotalBar(total = "12")
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            Box {
                FloatingActionButton(
                    onClick = navigateToAddProduct,
                    shape = CircleShape,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(80.dp)
                        .offset(y = 50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(45.dp)
                    )
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(10){
                ProductCard()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CartScreenContentPreview() {
    ShoppingCartTheme {
        CartScreenContent(
            navigateToAddProduct = {},
        )
    }
}