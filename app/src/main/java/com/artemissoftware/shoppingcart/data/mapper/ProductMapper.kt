package com.artemissoftware.shoppingcart.data.mapper

import com.artemissoftware.shoppingcart.data.database.entities.ProductEntity
import com.artemissoftware.shoppingcart.data.network.dto.HitDto
import com.artemissoftware.shoppingcart.domain.models.Product
import com.artemissoftware.shoppingcart.data.util.PriceUtil
import java.util.Locale

internal fun HitDto.toProduct(name: String? = null, price: Double = PriceUtil.generateRandomPrice(minPrice = 10.0, maxPrice = 100.0)): Product{
    return Product(
        id = id,
        title = (name ?: tags.split(",")[0]).replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        },
        imageUrl = previewURL,
        quantity = 1,
        price = price,
        description = tags,
    )
}

internal fun List<HitDto>.toProduct(name: String? = null, price: Double = PriceUtil.generateRandomPrice(minPrice = 10.0, maxPrice = 100.0)): Product? {
    if(this.isEmpty())
        return null

    return this.first().toProduct(name = name, price = price)
}

internal fun Product.toEntity(): ProductEntity{
    return ProductEntity(
        id = id,
        name = title,
        price = price,
        amount = quantity,
        imageUrl = imageUrl,
        description = description,
        commentary = comments
    )
}

internal fun ProductEntity.toProduct(): Product{
    return Product(
        id = id,
        title = name,
        price = price,
        imageUrl = imageUrl,
        quantity = amount,
        description = description,
        comments = commentary,
    )
}
