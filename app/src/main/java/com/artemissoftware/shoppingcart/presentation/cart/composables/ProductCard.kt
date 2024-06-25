package com.artemissoftware.shoppingcart.presentation.cart.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.artemissoftware.shoppingcart.PreviewData
import com.artemissoftware.shoppingcart.R
import com.artemissoftware.shoppingcart.domain.models.Product
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme

@Composable
internal fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
) {
    Card(
        modifier = modifier
            .clickable {
                onClick(product.id)
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ){
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp)),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(product.imageUrl)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = product.title,
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        fontWeight = FontWeight.Bold,
                        text = product.totalPrice().toString(),
                    )

                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = stringResource(id = R.string.qty),
                        )
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = product.quantity.toString(),
                        )
                    }
                }

                Text(
                    fontWeight = FontWeight.Bold,
                    text = product.comments,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductCardPreview() {
    ShoppingCartTheme {
        ProductCard(
            product = PreviewData.product,
            modifier = Modifier.fillMaxWidth(),
            onClick = { }
        )
    }
}