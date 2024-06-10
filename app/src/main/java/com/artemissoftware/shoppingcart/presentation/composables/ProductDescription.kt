package com.artemissoftware.shoppingcart.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemissoftware.shoppingcart.PreviewData
import com.artemissoftware.shoppingcart.R
import com.artemissoftware.shoppingcart.domain.models.Product
import com.artemissoftware.shoppingcart.presentation.composables.TestTags.PRODUCT_DESCRIPTION_CONTENT
import com.artemissoftware.shoppingcart.presentation.composables.TestTags.PRODUCT_DESCRIPTION_PRICE
import com.artemissoftware.shoppingcart.presentation.composables.TestTags.PRODUCT_DESCRIPTION_TITLE
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme

@Composable
internal fun ProductDescription(
    product: Product,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .testTag(PRODUCT_DESCRIPTION_CONTENT),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.name),
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = product.title,
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier
                    .testTag(PRODUCT_DESCRIPTION_TITLE)
                    .fillMaxWidth()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.price),
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = product.price.toString(),
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier
                    .testTag(PRODUCT_DESCRIPTION_PRICE)
                    .fillMaxWidth()
            )
        }
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