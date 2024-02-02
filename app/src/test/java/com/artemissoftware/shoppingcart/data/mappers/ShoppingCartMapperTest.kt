package com.artemissoftware.shoppingcart.data.mappers

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.shoppingcart.TestShoppingItemData.shoppingItem
import com.artemissoftware.shoppingcart.TestShoppingItemData.shoppingItemEntity
import org.junit.jupiter.api.Test

class ShoppingCartMapperTest {

    @Test
    fun `Map ShoppingItem to ShoppingItemEntity`() {
        assertThat(shoppingItem.toEntity()).isEqualTo(shoppingItemEntity)
    }
}
