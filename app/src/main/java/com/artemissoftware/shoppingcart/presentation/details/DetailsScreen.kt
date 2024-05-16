package com.artemissoftware.shoppingcart.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.artemissoftware.shoppingcart.PreviewData
import com.artemissoftware.shoppingcart.R
import com.artemissoftware.shoppingcart.presentation.composables.ProductDescription
import com.artemissoftware.shoppingcart.presentation.addproduct.composables.ProductDetail
import com.artemissoftware.shoppingcart.presentation.details.composables.ProductComments
import com.artemissoftware.shoppingcart.ui.theme.PrimaryColor
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme

@Composable
fun DetailsScreen(
    onPopBackStack: () -> Unit,
) {
//    val state = viewModel.state.collectAsState().value
//
//    DetailsScreenContent(
//        state = state,
//        event = viewModel::onTriggerEvent,
//        onPopBackStack = onPopBackStack,
//    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsScreenContent(
    state: DetailsState,
    event: (DetailsEvent) -> Unit,
    onPopBackStack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = topAppBarColors(containerColor = PrimaryColor),
                navigationIcon = {
                    IconButton(onClick = onPopBackStack) {
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
    ) { paddingValues ->
        state.product?.let {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = PrimaryColor)
                    .padding(paddingValues)
            ) {
                val (productDescription, backgroundColor, productImage,  productDetail) = createRefs()

                ProductDescription(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3F)
                        .padding(all = 16.dp)
                        .constrainAs(productDescription) {},
                    product = it
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7F)
                        .background(
                            Color.White,
                            RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                        )
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                        .constrainAs(backgroundColor) {
                            top.linkTo(productDescription.bottom)
                            start.linkTo(productDescription.start)
                        }
                )

                AsyncImage(
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .constrainAs(productImage) {
                            top.linkTo(productDescription.bottom)
                            bottom.linkTo(backgroundColor.top, 60.dp)
                            end.linkTo(parent.end, 20.dp)
                        },
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(it.imageUrl)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )

                ProductComments(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .constrainAs(productDetail) {
                            top.linkTo(productImage.bottom)
                            start.linkTo(productDescription.start)
                        },
                    product = it,
                    onSave = { comment, promoCode ->

                    }
                )
            }
        } ?: run {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailsScreenContentPreview() {
    ShoppingCartTheme {
        DetailsScreenContent(
            onPopBackStack = {},
            event = {},
            state = PreviewData.detailsState
        )
    }
}
