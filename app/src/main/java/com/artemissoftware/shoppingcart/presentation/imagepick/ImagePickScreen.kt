package com.artemissoftware.shoppingcart.presentation.imagepick

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.artemissoftware.shoppingcart.R
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme

@Composable
fun ImagePickScreen() {
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ImagePickContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        OutlinedTextField(
            value = "value",
            onValueChange = { newText ->
                // value = newText
            },
            label = { Text(text = "Email") },
            placeholder = { Text(text = "Type your email") },
        )

        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(count = 4),
            verticalArrangement = Arrangement.spacedBy(space = 8.dp), // top and bottom margin between each item
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp), // left and right margin between each item
        ) {
            items(count = 100) { index ->
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
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ImagePickContentPreview() {
    ShoppingCartTheme {
        ImagePickContent()
    }
}
