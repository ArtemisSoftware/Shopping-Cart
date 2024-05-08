package com.artemissoftware.shoppingcart.presentation.cart.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.shoppingcart.R
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
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
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Red),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "",
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = "Name",
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        fontWeight = FontWeight.Bold,
                        text = "total price",
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
                            text = "2",
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductCardPreview() {
    ShoppingCartTheme {
        ProductCard(
            modifier = Modifier.fillMaxWidth()
        )
    }
}