package com.artemissoftware.shoppingcart.data.repositories

import com.artemissoftware.shoppingcart.TestShoppingItemData.shoppingItem
import com.artemissoftware.shoppingcart.TestShoppingItemData.shoppingItemEntity
import com.artemissoftware.shoppingcart.data.database.ShoppingDao
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ShoppingCartRepositoryImplTest {

    private lateinit var repository: ShoppingCartRepositoryImpl
    private lateinit var shoppingDao: ShoppingDao

    @BeforeEach
    fun setUp() {
        shoppingDao = mockk()
        repository = ShoppingCartRepositoryImpl(shoppingDao = shoppingDao)
    }

    @Test
    fun `Insert item, check dao is called`() = runBlocking {
        coEvery { shoppingDao.insertShoppingItem(shoppingItemEntity) } returns Unit

        repository.insertShoppingItem(shoppingItem)

        coVerify {
            shoppingDao.insertShoppingItem(shoppingItemEntity)
        }
    }
}
