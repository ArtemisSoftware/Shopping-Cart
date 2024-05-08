package com.artemissoftware.shoppingcart.presentation.additem

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.artemissoftware.shoppingcart.PreviewData
import com.artemissoftware.shoppingcart.PreviewData.product
import com.artemissoftware.shoppingcart.presentation.additem.composables.ProductDescription
import com.artemissoftware.shoppingcart.presentation.additem.composables.ProductDetail
import com.artemissoftware.shoppingcart.ui.theme.PrimaryColor
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme

@Composable
fun AddProductScreen(
    onPopBackStack: () -> Unit,
) {
    AddProductScreenContent(
        onPopBackStack = onPopBackStack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddProductScreenContent(
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
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            modifier = Modifier
                                .size(25.dp),
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = PrimaryColor)
                .padding(it)
        ) {
            val (productDescription, productDetail, productImage,  xxx) = createRefs()

            ProductDescription(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3F)
                    .padding(all = 16.dp)
                    .constrainAs(productDescription) {},
                product = product
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7F)
                    .background(Color.White, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .constrainAs(productDetail) {
                        top.linkTo(productDescription.bottom)
                        start.linkTo(productDescription.start)
                    }
            )

            Image(
                painter = painterResource(id = product.img),
                contentDescription = "Product Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(180.dp)
                    .constrainAs(productImage) {
                        top.linkTo(productDescription.bottom)
                        bottom.linkTo(productDetail.top, 60.dp)
                        end.linkTo(parent.end, 20.dp)
                    }
            )

            ProductDetail(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .constrainAs(xxx) {
                        top.linkTo(productImage.bottom)
                        start.linkTo(productDescription.start)
                    },
                product = product,
                onBuyNow = {}
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun AddProductScreenPreview() {
    ShoppingCartTheme {
        AddProductScreenContent(
            onPopBackStack = {}
        )
    }
}