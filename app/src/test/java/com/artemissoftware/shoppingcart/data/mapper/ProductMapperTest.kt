package com.artemissoftware.shoppingcart.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.artemissoftware.shoppingcart.ProductTestData.comments
import com.artemissoftware.shoppingcart.ProductTestData.getProduct
import com.artemissoftware.shoppingcart.ProductTestData.hitDto
import com.artemissoftware.shoppingcart.ProductTestData.productEntity
import com.artemissoftware.shoppingcart.ProductTestData.productName
import com.artemissoftware.shoppingcart.ProductTestData.productPrice
import com.artemissoftware.shoppingcart.data.network.dto.HitDto
import org.junit.jupiter.api.Test

internal class ProductMapperTest {

    @Test
    fun `Map HitDto to product`(){
        assertThat(hitDto.toProduct())
            .isEqualTo(getProduct())
    }

    @Test
    fun `Map HitDto to product with established name`(){
        assertThat(hitDto.toProduct(name = productName))
            .isEqualTo(getProduct(name = productName))
    }

    @Test
    fun `Map List HitDto to product with established name`(){
        assertThat(listOf(hitDto).toProduct(name = productName))
            .isEqualTo(getProduct(name = productName))
    }

    @Test
    fun `Map empty List HitDto to null product`(){
        assertThat(listOf<HitDto>().toProduct(name = productName))
            .isNull()
    }

    @Test
    fun `Map Product to ProductEntity`(){
        assertThat(getProduct(name = productName, commentary = comments).toEntity())
            .isEqualTo(productEntity)
    }

    @Test
    fun `Map ProductEntity to Product`(){
        assertThat(productEntity.toProduct())
            .isEqualTo(getProduct(name = productName, commentary = comments))
    }
}