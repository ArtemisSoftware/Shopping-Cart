package com.artemissoftware.shoppingcart.presentation.details.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemissoftware.shoppingcart.PreviewData
import com.artemissoftware.shoppingcart.R
import com.artemissoftware.shoppingcart.domain.models.Product
import com.artemissoftware.shoppingcart.presentation.addproduct.composables.ProductDetail
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme

@Composable
internal fun ProductComments(
    product: Product,
    onSave: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var comment by remember {
        mutableStateOf("")
    }

    var promoCode by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        TextField(
            value = comment,
            onValueChange = { comment = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
        )

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.promo_code),
                modifier = Modifier,
                color = Color.Gray
            )

            Text(
                text = stringResource(id = R.string.summer),
                modifier = Modifier,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

            OutlinedTextField(
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                value = promoCode,
                onValueChange = { promoCode = it },
                label = {  },
                modifier = Modifier
                    .width(60.dp),
            )
        }

        Button(
            onClick = {
                onSave(comment, promoCode)
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.save),
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
private fun ProductCommentsPreview() {
    ShoppingCartTheme {
        ProductComments(
            product = PreviewData.product,
            onSave = {_, _ ->},
        )
    }
}