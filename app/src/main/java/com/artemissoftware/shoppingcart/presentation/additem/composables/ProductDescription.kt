package com.artemissoftware.shoppingcart.presentation.additem.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemissoftware.shoppingcart.PreviewData
import com.artemissoftware.shoppingcart.domain.models.Product
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme

@Composable
internal fun ProductDescription(
    product: Product,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {

        Text(
            text = "Aristocratic Hand Bag",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
        )

        Text(
            text = product.title,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(start = 16.dp)
        )

        Text(
            text = "Price",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 16.dp)
        )

        Text(
            text = product.price,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 8.dp, top = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDescriptionPreview() {
    ShoppingCartTheme {
        ProductDescription(
            modifier = Modifier.fillMaxWidth(),
            product = PreviewData.product
        )
    }
}