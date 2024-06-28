package com.artemissoftware.data.mapper


import com.artemissoftware.database.entities.ProductEntity
import com.artemissoftware.models.Product
import com.artemissoftware.network.dto.HitDto
import java.util.Locale

internal fun HitDto.toProduct(name: String? = null): Product {
    return Product(
        id = id,
        title = (name ?: tags.split(",")[0]).replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        },
        imageUrl = previewURL,
        quantity = 1,
        price = likes.toDouble(),
        description = tags,
    )
}

internal fun List<HitDto>.toProduct(name: String? = null): Product? {
    if(this.isEmpty())
        return null

    return this.first().toProduct(name = name)
}

internal fun Product.toEntity(): ProductEntity {
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
