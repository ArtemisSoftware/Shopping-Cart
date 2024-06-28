package com.artemissoftware.product.searchproduct

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.artemissoftware.shoppingcart.PreviewData
import com.artemissoftware.shoppingcart.R
import com.artemissoftware.product.searchproduct.TestTags.SEARCH_PRODUCT_BACK_BUTTON
import com.artemissoftware.product.searchproduct.TestTags.SEARCH_PRODUCT_ERROR
import com.artemissoftware.product.searchproduct.TestTags.SEARCH_PRODUCT_GRID
import com.artemissoftware.product.searchproduct.TestTags.SEARCH_PRODUCT_SEARCH_BAR
import com.artemissoftware.product.searchproduct.TestTags.searchProductItemTag
import com.artemissoftware.product.searchproduct.composables.SearchToolbar
import com.artemissoftware.shoppingcart.ui.theme.PrimaryColor
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme
import kotlinx.coroutines.launch

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

    val controller = LocalSoftwareKeyboardController.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutine = rememberCoroutineScope()

    LaunchedEffect(state.snackBarState) {
        state.snackBarState?.let { snack ->
            coroutine.launch {
                snackbarHostState.showSnackbar(
                    message = snack.title,
                    withDismissAction = true,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                modifier = Modifier
                    .testTag(SEARCH_PRODUCT_ERROR),
                hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryColor
                ),
                navigationIcon = {
                    IconButton(
                        modifier = Modifier
                            .testTag(SEARCH_PRODUCT_BACK_BUTTON),
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

            SearchToolbar(
                searchQuery = state.searchQuery,
                modifier = Modifier
                    .testTag(SEARCH_PRODUCT_SEARCH_BAR)
                    .fillMaxWidth()
                    .padding(16.dp),
                onBackClick = {},
                onSearchQueryChanged = {
                    event(SearchProductEvent.UpdateQuery(it))
                },
                onSearchTriggered = {
                    event(SearchProductEvent.Search)
                    controller?.hide()
                },
            )

            LazyVerticalStaggeredGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(SEARCH_PRODUCT_GRID),
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(12.dp),
                content = {
                    itemsIndexed(state.products) { index, product ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                            modifier = Modifier
                                .testTag(searchProductItemTag(index))
                                .clip(RoundedCornerShape(12.dp))
                                .clickable {
                                    navigateToAddProduct(product.id)
                                }
                                .fillMaxWidth()
                                .padding(4.dp),
                        ) {

                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                model = ImageRequest
                                    .Builder(LocalContext.current)
                                    .data(product.imageUrl)
                                    .placeholder(R.drawable.ic_launcher_foreground)
                                    .build(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
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