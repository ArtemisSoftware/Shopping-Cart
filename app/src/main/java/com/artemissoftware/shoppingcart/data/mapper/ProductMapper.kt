package com.artemissoftware.shoppingcart.data.mapper

import com.artemissoftware.shoppingcart.data.database.entities.ProductEntity
import com.artemissoftware.shoppingcart.data.network.dto.HitDto
import com.artemissoftware.shoppingcart.domain.models.Product
import kotlin.random.Random

fun HitDto.toProduct(name: String): Product{
    return Product(
        id = id,
        title = name,
        imageUrl = imageURL,
        previewUrl = previewURL,
        quantity = 0,
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
        previewUrl = previewUrl,
        description = description,
    )
}

fun ProductEntity.toProduct(): Product{
    return Product(
        id = id,
        title = name,
        price = price,
        imageUrl = imageUrl,
        previewUrl = previewUrl,
        quantity = amount,
        description = description,
    )
}
