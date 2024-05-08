package com.artemissoftware.shoppingcart.presentation.cart.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.shoppingcart.R
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme

@Composable
internal fun TotalBar(
    total: String,
    modifier: Modifier = Modifier,
) {
    BottomAppBar(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp)),
    ) {
        Row(
            modifier = Modifier
                .padding(all = 12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.total_cost),
            )
            Text(
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                text = total,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TotalBarPreview() {
    ShoppingCartTheme {
        TotalBar(
            total = "11",
            modifier = Modifier.fillMaxWidth()
        )
    }
}