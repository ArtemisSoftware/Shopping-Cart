package com.artemissoftware.shoppingcart.presentation.additem.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemissoftware.shoppingcart.PreviewData.product
import com.artemissoftware.shoppingcart.domain.models.Product
import com.artemissoftware.shoppingcart.R
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme

@Composable
internal fun ProductDetail(
    product: Product,
    onBuyNow: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = product.description,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Gray
        )

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            IconButton(onClick = {/* Do Something*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Remove,
                    contentDescription = null,
                    modifier = Modifier.size(45.dp)
                )
            }

            Text(
                text = "01",
                fontSize = 23.sp,
                color = Color.DarkGray,
                modifier = Modifier
            )

            IconButton(onClick = {/* Do Something*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null,
                    modifier = Modifier.size(45.dp)
                )
            }
        }

        Button(
            onClick = onBuyNow,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.buy_now),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailPreview() {
    ShoppingCartTheme {
        ProductDetail(
            product = product,
            onBuyNow = {},
        )
    }
}