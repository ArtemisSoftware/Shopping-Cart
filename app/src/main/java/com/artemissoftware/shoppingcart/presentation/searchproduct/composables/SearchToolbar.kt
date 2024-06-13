package com.artemissoftware.shoppingcart.presentation.searchproduct.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.shoppingcart.presentation.searchproduct.SearchProductEvent
import com.artemissoftware.shoppingcart.presentation.searchproduct.TestTags.SEARCH_PRODUCT_SEARCH_ICON

@Composable
fun SearchToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    searchQuery: String = "",
    onSearchTriggered: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,

    ) {
        SearchTextField(
            modifier = modifier.fillMaxWidth(),
            onSearchQueryChanged = onSearchQueryChanged,
            onSearchTriggered = onSearchTriggered,
            searchQuery = searchQuery,
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchTextField(
    modifier: Modifier = Modifier,
    onSearchQueryChanged: (String) -> Unit,
    searchQuery: String,
    onSearchTriggered: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    TextField(
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        trailingIcon = {
            IconButton(
                modifier = Modifier.testTag(
                    SEARCH_PRODUCT_SEARCH_ICON
                ),
                onClick = {
                    if(searchQuery.isNotEmpty()) {
                        onSearchTriggered()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp),
                    tint = Color.Black
                )
            }
        },
        onValueChange = {
            if ("\n" !in it) onSearchQueryChanged(it)
        },
        modifier = modifier
            .focusRequester(focusRequester),
        shape = RoundedCornerShape(32.dp),
        value = searchQuery,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        maxLines = 1,
        singleLine = true,
    )
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Preview
@Composable
private fun SearchToolbarPreview() {
    SearchToolbar(
        modifier = Modifier.fillMaxWidth(),
        onBackClick = {},
        onSearchQueryChanged = {},
        onSearchTriggered = {},
    )

}