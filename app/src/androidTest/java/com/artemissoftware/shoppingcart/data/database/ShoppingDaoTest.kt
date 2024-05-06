package com.artemissoftware.shoppingcart.data.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import assertk.all
import assertk.assertThat
import assertk.assertions.doesNotContain
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import com.artemissoftware.shoppingcart.TestShoppingItemData
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
internal class ShoppingDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var database: ShoppingCartDatabase
    private lateinit var shoppingDao: ShoppingDao

    @Before
    fun setUp() {
        hiltRule.inject()
        shoppingDao = database.shoppingDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `Add shopping item`() = runTest {
        shoppingDao.insertShoppingItem(TestShoppingItemData.shoppingItem)

        val allShoppingItem = shoppingDao.getAllShoppingItems()

        allShoppingItem.test {
            val list = awaitItem()
            assertThat(list).isEqualTo(listOf(TestShoppingItemData.shoppingItem))
            cancel()
        }
    }

    @Test
    fun `Delete shopping item`() = runTest {
        shoppingDao.insertShoppingItem(TestShoppingItemData.shoppingItem)
        shoppingDao.deleteShoppingItem(TestShoppingItemData.shoppingItem)

        val allShoppingItem = shoppingDao.getAllShoppingItems()

        allShoppingItem.test {
            val list = awaitItem()

            assertThat(list).all {
                doesNotContain(TestShoppingItemData.shoppingItem)
                hasSize(0)
            }
            cancel()
        }
    }

    @Test
    fun `Insert items and check total price sum`() = runTest {
        TestShoppingItemData.shoppingItemList.forEach { shoppingItem ->
            shoppingDao.insertShoppingItem(shoppingItem)
        }

        val totalPriceSum = shoppingDao.getTotalPrice()
        totalPriceSum.test {
            val total = awaitItem()
            assertThat(total)
                .isEqualTo(TestShoppingItemData.shoppingItemList.map { it.price * it.amount }.sum())
            cancel()
        }
    }
}
