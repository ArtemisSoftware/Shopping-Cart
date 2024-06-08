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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemissoftware.shoppingcart.R
import com.artemissoftware.shoppingcart.presentation.cart.TestTags
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
                .testTag(TestTags.TOTAL_CONTENT)
                .padding(all = 12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.testTag(TestTags.TOTAL_DESCRIPTION),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                text = stringResource(id = R.string.total_cost),
            )
            Text(
                modifier = Modifier.testTag(TestTags.TOTAL_VALUE),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
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