package com.artemissoftware.shoppingcart.data.database

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import assertk.all
import assertk.assertThat
import assertk.assertions.doesNotContain
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import com.artemissoftware.shoppingcart.TestShoppingItemData
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ShoppingDaoTest {

//    @get:Rule
//    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

//    @Inject
//    @Named("test_db")
    lateinit var database: ShoppingCartDatabase
    private lateinit var shoppingDao: ShoppingDao

    @BeforeEach
    fun setUp() {
//        hiltRule.inject()

        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room
            .inMemoryDatabaseBuilder(context, ShoppingCartDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        shoppingDao = database.shoppingDao()
    }

    @AfterEach
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
