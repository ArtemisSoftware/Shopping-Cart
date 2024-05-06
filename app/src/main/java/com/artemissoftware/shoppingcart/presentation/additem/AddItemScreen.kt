package com.artemissoftware.shoppingcart.presentation.additem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.artemissoftware.shoppingcart.R
import com.artemissoftware.shoppingcart.presentation.composables.CircularIcon
import com.artemissoftware.shoppingcart.ui.theme.Background
import com.artemissoftware.shoppingcart.ui.theme.DarkGray
import com.artemissoftware.shoppingcart.ui.theme.DarkGreen
import com.artemissoftware.shoppingcart.ui.theme.Gray
import com.artemissoftware.shoppingcart.ui.theme.Gray400
import com.artemissoftware.shoppingcart.ui.theme.Gray500
import com.artemissoftware.shoppingcart.ui.theme.LightRed
import com.artemissoftware.shoppingcart.ui.theme.MediumFont
import com.artemissoftware.shoppingcart.ui.theme.RegularFont
import com.artemissoftware.shoppingcart.ui.theme.ShoppingCartTheme

@Composable
fun AddItemScreen(viewModel: AddItemViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState().value
    AddItemContent(
        state = state,
        events = viewModel::onTriggerEvent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddItemContent(
    state: AddItemState,
    events: (AddItemEvents) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            Header(onBackClick = {})
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(all = 12.dp)
                .background(Background),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    item {
                        ShowProduct(
                            state = state,
                            onQuantityChange = {
                                events.invoke(AddItemEvents.UpdateAmount(it))
                            },
                            modifier = Modifier
                                .padding(top = 30.dp)
                                .fillMaxWidth()
                        )
                    }
                    item {
                        CustomSearchBar(
                            value = state.name,
                            onValueChange = { name ->
                                events.invoke(AddItemEvents.UpdateName(name))
                            },
                        )
                    }
                    item {
                        ProductDescription(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 15.dp)
                        )
                    }
                }
            }

            Button(
                onClick = {
                    events.invoke(AddItemEvents.InsertItem)
                },
                modifier = Modifier.padding(20.dp).align(Alignment.BottomCenter).fillMaxWidth(),
                elevation = ButtonDefaults.buttonElevation(),
                shape = RoundedCornerShape(37.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkGreen,
                ),
            ) {
                Text(
                    text = stringResource(R.string.add),
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.W500,
                        fontSize = 20.sp,
                        fontFamily = MediumFont,
                    ),
                    modifier = Modifier.padding(vertical = 5.dp),
                )
            }
        }

        /*
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(MaterialTheme.shapes.medium),
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(state.imageUrl)
                        .placeholder(R.drawable.ic_image_placeholder)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )

                OutlinedTextField(
                    value = state.name,
                    onValueChange = { name ->
                        events.invoke(AddItemEvents.UpdateName(name))
                    },
                    placeholder = { Text(text = stringResource(R.string.name)) },
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(0.5F),
                    value = state.amount,
                    onValueChange = { amount ->
                        events.invoke(AddItemEvents.UpdateAmount(amount))
                    },
                    placeholder = { Text(text = stringResource(R.string.amount)) },
                )

                OutlinedTextField(
                    modifier = Modifier.weight(0.5F),
                    value = state.price,
                    onValueChange = { price ->
                        events.invoke(AddItemEvents.UpdatePrice(price))
                    },
                    placeholder = { Text(text = stringResource(R.string.price)) },
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                FilledTonalButton(
                    modifier = Modifier
                        .width(200.dp)
                        .align(Alignment.CenterEnd),
                    onClick = {
                        events.invoke(AddItemEvents.InsertItem)
                    },
                ) {
                    Text(text = stringResource(R.string.add))
                }
            }
        }
        */
    }
}

@Composable
private fun ShowProduct(
    state: AddItemState,
    onQuantityChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var counter by remember { mutableStateOf(0) }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(LightRed),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            AsyncImage(
                modifier = Modifier
                    .height(220.dp)
                    .clip(MaterialTheme.shapes.medium),
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(state.imageUrl)
                    .placeholder(R.drawable.ic_image_placeholder)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .width(130.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White),
            ) {
                CircularIcon(
                    icon = R.drawable.plus,
                    background = DarkGreen,
                    onClick = {
                        counter++
                        onQuantityChange(counter.toString())
                    },
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "$counter",
                    style = TextStyle(
                        color = DarkGreen,
                        fontWeight = FontWeight.W400,
                        fontSize = 25.sp,
                        fontFamily = MediumFont,
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Center),
                )
                Spacer(modifier = Modifier.width(10.dp))
                CircularIcon(
                    icon = R.drawable.minus,
                    background = DarkGreen,
                    modifier = Modifier.align(
                        TopEnd,
                    ),
                    onClick = {
                        if (counter > 0) {
                            counter--
                        }

                        onQuantityChange(counter.toString())
                    },
                )
            }
        }
    }
}

@Composable
private fun ProductDescription(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Coffee",
                style = TextStyle(
                    color = DarkGreen,
                    fontWeight = FontWeight.W400,
                    fontSize = 18.sp,
                    fontFamily = MediumFont,
                ),
            )

            Text(
                text = "$20.00",
                style = TextStyle(
                    color = DarkGreen,
                    fontWeight = FontWeight.W400,
                    fontSize = 18.sp,
                    fontFamily = MediumFont,
                ),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Chocolate Frappuccino",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.W500,
                    fontSize = 22.sp,
                    fontFamily = MediumFont,
                ),
            )
            Row(
                modifier = Modifier.align(CenterVertically),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .align(CenterVertically),
                    tint = Color.Unspecified,
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "4.5",
                    style = TextStyle(
                        color = Gray400,
                        fontWeight = FontWeight.W400,
                        fontSize = 18.sp,
                        fontFamily = MediumFont,
                    ),
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam at mi vitae augue feugiat scelerisque in a eros.",
            style = TextStyle(
                color = Gray500,
                fontWeight = FontWeight.W400,
                fontSize = 18.sp,
                fontFamily = MediumFont,
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(26.5.dp))
            .background(Gray),
    ) {
        TextField(
            value = value,
            onValueChange = { onValueChange(it) },
            placeholder = {
                Text(
                    text = "Search",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = RegularFont,
                        fontWeight = FontWeight.W400,
                        color = DarkGray,
                    ),
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "",
                    tint = Color.Unspecified,
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            trailingIcon = {},
        )
    }
}

// @Composable
// fun AppButton(
//    modifier: Modifier = Modifier
// ) {
//
//    Button(
//        onClick = {}, modifier = modifier.fillMaxWidth(),
//        elevation = ButtonDefaults.elevation(0.dp),
//        shape = RoundedCornerShape(37.dp),
//        colors = ButtonDefaults.buttonColors(
//            backgroundColor = DarkGreen
//        ),
//    ) {
//        Text(
//            text = stringResource(R.string.add_to_bag),
//            style = TextStyle(
//                color = Color.White,
//                fontWeight = FontWeight.W500,
//                fontSize = 20.sp,
//                fontFamily = MediumFont
//            ),
//            modifier = Modifier.padding(vertical = 5.dp)
//        )
//    }
//
// }

@Composable
private fun Header(
    onBackClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CircularIcon(
            icon = R.drawable.back,
            onClick = onBackClick,
        )
        Icon(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            modifier = Modifier.size(58.dp),
            tint = Color.Unspecified,
        )
        CircularIcon(
            icon = R.drawable.love,
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddItemContentPreview() {
    ShoppingCartTheme {
        AddItemContent(
            state = AddItemState(
                name = "Churrasqueira",
            ),
            events = {},
        )
    }
}
