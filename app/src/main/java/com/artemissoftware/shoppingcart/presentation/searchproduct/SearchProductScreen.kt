package com.artemissoftware.shoppingcart.presentation.searchproduct

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.shoppingcart.PreviewData
import com.artemissoftware.shoppingcart.R
import com.artemissoftware.shoppingcart.ui.theme.PrimaryColor
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme

@Composable
fun SearchProductScreen(
    onPopBackStack: () -> Unit,
    navigateToAddProduct: (Int) -> Unit,
    viewModel: SearchProductViewModel = hiltViewModel(),
) {

    val state = viewModel.state.collectAsState().value

    SearchProductScreenContent(
        state = state,
        event = viewModel::onEventTrigger,
        onPopBackStack = onPopBackStack,
        navigateToAddProduct = navigateToAddProduct,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchProductScreenContent(
    state: SearchProductState,
    event: (SearchProductEvent) -> Unit,
    onPopBackStack: () -> Unit,
    navigateToAddProduct: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryColor
                ),
                navigationIcon = {
                    IconButton(
                        onClick = onPopBackStack
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back Arrow",
                            modifier = Modifier
                                .size(25.dp),
                            tint = Color.White
                        )
                    }
                },
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            SearchBar(
                query = state.searchQuery,
                onQueryChange = { event(SearchProductEvent.UpdateQuery(it)) }, //update the value of searchText
                trailingIcon = {
                    IconButton(
                        onClick = {
                            event(SearchProductEvent.Search)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            modifier = Modifier
                                .size(25.dp),
                            tint = Color.Black
                        )
                    }
                },
                onSearch = {

                }, //the callback to be invoked when the input service triggers the ImeAction.Search action
                active = false, //whether the user is searching or not
                onActiveChange = {  }, //the callback to be invoked when this search bar's active state is changed
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){}

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(12.dp),
                content = {
                    items(state.products) { product ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                            modifier = Modifier
                                .clickable {
                                    navigateToAddProduct(product.id)
                                }
                                .fillMaxWidth()
                                .padding(4.dp),
                        ) {
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .size(160.dp)
                                    .background(Color.Red)
                                    .padding(8.dp),
                                painter = painterResource(id = product.img),
                                contentDescription = "",
                            )
                        }
                    }
                }
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchProductScreenContentPreview() {
    ShoppingCartTheme {
        SearchProductScreenContent(
            state = PreviewData.searchProductState,
            event = {},
            onPopBackStack = {},
            navigateToAddProduct = {},
        )
    }
}