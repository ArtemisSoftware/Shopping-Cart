package com.artemissoftware.product.addproduct.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemissoftware.models.Product
import com.artemissoftware.product.PreviewData
import com.artemissoftware.shoppingcart.presentation.addproduct.TestTags.PRODUCT_DETAIL_ADD_BUTTON
import com.artemissoftware.shoppingcart.presentation.addproduct.TestTags.PRODUCT_DETAIL_BUY_BUTTON
import com.artemissoftware.shoppingcart.presentation.addproduct.TestTags.PRODUCT_DETAIL_CONTENT
import com.artemissoftware.shoppingcart.presentation.addproduct.TestTags.PRODUCT_DETAIL_DESCRIPTION
import com.artemissoftware.shoppingcart.presentation.addproduct.TestTags.PRODUCT_DETAIL_QUANTITY
import com.artemissoftware.shoppingcart.presentation.addproduct.TestTags.PRODUCT_DETAIL_REMOVE_BUTTON
import com.artemissoftware.ui.R
import com.artemissoftware.ui.theme.ShoppingCartTheme

@Composable
internal fun ProductDetail(
    product: Product,
    onRemoveQuantity: () -> Unit,
    onAddQuantity: () -> Unit,
    onBuyNow: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .testTag(PRODUCT_DETAIL_CONTENT),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = product.description,
            modifier = Modifier
                .testTag(PRODUCT_DETAIL_DESCRIPTION)
                .fillMaxWidth(),
            color = Color.Gray
        )

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            IconButton(onClick = onRemoveQuantity) {
                Icon(
                    imageVector = Icons.Rounded.Remove,
                    contentDescription = null,
                    modifier = Modifier
                        .testTag(PRODUCT_DETAIL_REMOVE_BUTTON)
                        .size(45.dp)
                )
            }

            Text(
                text = product.quantity.toString(),
                fontSize = 23.sp,
                color = Color.DarkGray,
                modifier = Modifier
                    .testTag(PRODUCT_DETAIL_QUANTITY)
            )

            IconButton(onClick = onAddQuantity) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null,
                    modifier = Modifier
                        .testTag(PRODUCT_DETAIL_ADD_BUTTON)
                        .size(45.dp)
                )
            }
        }

        Button(
            onClick = onBuyNow,
            modifier = Modifier
                .testTag(PRODUCT_DETAIL_BUY_BUTTON)
                .fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.buy_now),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 30.sp,
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
            product = PreviewData.product,
            onBuyNow = {},
            onRemoveQuantity = {},
            onAddQuantity = {},
        )
    }
}