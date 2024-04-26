package com.artemissoftware.shoppingcart.presentation.shopping.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.artemissoftware.shoppingcart.R
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme

@Composable
fun ShopItem(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest
                .Builder(LocalContext.current)
                .data("article.urlToImage")
                .placeholder(R.drawable.ic_launcher_background)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = "300x",
                    fontSize = 28.sp,
                )
                Text(
                    text = "Product name",
                    fontSize = 28.sp,
                )
            }
            Text(
                text = "price",
                fontSize = 20.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShopItemPreview() {
    ShoppingCartTheme {
        ShopItem(
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
