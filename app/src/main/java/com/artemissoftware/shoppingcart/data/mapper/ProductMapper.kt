package com.artemissoftware.shoppingcart.data.mapper

import com.artemissoftware.shoppingcart.data.database.entities.ProductEntity
import com.artemissoftware.shoppingcart.data.network.dto.HitDto
import com.artemissoftware.shoppingcart.domain.models.Product
import com.artemissoftware.shoppingcart.data.util.PriceUtil
import java.util.Locale

internal fun HitDto.toProduct(name: String? = null): Product{
    return Product(
        id = id,
        title = (name ?: tags.split(",")[0]).replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        },
        imageUrl = previewURL,
        quantity = 1,
        price = PriceUtil.generateRandomPrice(minPrice = 10.0, maxPrice = 100.0),
        description = tags,
    )
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

