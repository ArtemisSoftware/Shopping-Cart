package com.artemissoftware.shoppingcart.presentation.shopping

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.shoppingcart.presentation.shopping.composables.ShopItem
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme

@Composable
fun ShoppingScreen() {
    ShoppingScreenContent()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShoppingScreenContent() {
    // val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
    val lll = listOf("materialBlue700", "materialBlue700", "materialBlue700", "materialBlue700", "materialBlue700")
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 12.dp),
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                },
            ) {
                Text("X")
            }
        },
        bottomBar = {
            Text("Total Cost: ")
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(items = lll) { item ->
                ShopItem(
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    ShoppingCartTheme {
        ShoppingScreenContent()
    }
}
