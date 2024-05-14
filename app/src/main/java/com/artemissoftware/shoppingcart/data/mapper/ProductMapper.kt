package com.artemissoftware.shoppingcart.data.mapper

import androidx.compose.ui.text.capitalize
import com.artemissoftware.shoppingcart.data.database.entities.ProductEntity
import com.artemissoftware.shoppingcart.data.network.dto.HitDto
import com.artemissoftware.shoppingcart.domain.models.Product
import java.util.Locale
import kotlin.random.Random

fun HitDto.toProduct(name: String? = null): Product{
    return Product(
        id = id,
        title = (name ?: tags.split(",")[0]).replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        },
        imageUrl = previewURL,
        quantity = 1,
        price = generateRandomPrice(minPrice = 10.0, maxPrice = 100.0),
        description = tags,
    )
}

private fun generateRandomPrice(minPrice: Double, maxPrice: Double): Double {
    require(minPrice < maxPrice) { "minPrice must be less than maxPrice" }

    val randomPrice = Random.nextDouble(minPrice, maxPrice)
    // Round to two decimal places
    return String.format("%.2f", randomPrice).toDouble()
}

fun Product.toEntity(): ProductEntity{
    return ProductEntity(
        id = id,
        name = title,
        price = price,
        amount = quantity,
        imageUrl = imageUrl,
        description = description,
    )
}

fun ProductEntity.toProduct(): Product{
    return Product(
        id = id,
        title = name,
        price = price,
        imageUrl = imageUrl,
        quantity = amount,
        description = description,
    )
}

