package com.artemissoftware.shoppingcart.presentation.searchitem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.shoppingcart.R
import com.artemissoftware.shoppingcart.ui.theme.PrimaryColor
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme

@Composable
fun SearchItemScreen(
    onPopBackStack: () -> Unit,
) {
    SearchItemScreenContent(
        onPopBackStack = onPopBackStack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchItemScreenContent(
    onPopBackStack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.smallTopAppBarColors(
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
                query = "searchText",
                onQueryChange = {}, //update the value of searchText
                onSearch = {}, //the callback to be invoked when the input service triggers the ImeAction.Search action
                active = false, //whether the user is searching or not
                onActiveChange = {  }, //the callback to be invoked when this search bar's active state is changed
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){}

            val list = (1..10).map { it.toString() }

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),

                // content padding
                contentPadding = PaddingValues(12.dp),
                content = {
                    items(list.size) { index ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                        ) {
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.Red),
                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
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
private fun AddProductScreenPreview() {
    ShoppingCartTheme {
        SearchItemScreenContent(
            onPopBackStack = {}
        )
    }
}