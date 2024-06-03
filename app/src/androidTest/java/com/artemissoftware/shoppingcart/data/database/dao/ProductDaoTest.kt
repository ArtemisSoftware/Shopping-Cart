package com.artemissoftware.shoppingcart.data.database.dao

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.artemissoftware.shoppingcart.InstrumentedProductTestData.productEntities
import com.artemissoftware.shoppingcart.InstrumentedProductTestData.productEntity
import com.artemissoftware.shoppingcart.data.database.ShoppingCartDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
internal class ProductDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var shoppingCartDatabase: ShoppingCartDatabase
    private lateinit var productDao: ProductDao

    @Before
    fun setUp(){
        hiltRule.inject()
        productDao = shoppingCartDatabase.getProductDao()
    }

    @After
    fun tearDown(){
        shoppingCartDatabase.close()
    }

    @Test
    fun `Insert a product`() = runBlocking {
        productDao.insert(productEntity = productEntity)

        val result = productDao.get(id = 1)

        assertThat(result)
            .isEqualTo(productEntity)
    }

    @Test
    fun `Delete a product`() = runBlocking {
        productDao.insert(productEntity = productEntity)

        productDao.delete(productEntity = productEntity)
        val result = productDao.get(id = 1)

        assertThat(result)
            .isNull()
    }

    @Test
    fun `Get all products`() = runBlocking  {

        productEntities.forEach { entity ->
            productDao.insert(productEntity = entity)
        }
        productDao.getAll().test {

            val list = awaitItem()
            assertThat(list)
                .hasSize(productEntities.size)
            assertThat(list)
                .isEqualTo(productEntities)
        }
    }

    @Test
    fun `Get total price`() = runBlocking {
        productEntities.forEach { entity ->
            productDao.insert(productEntity = entity)
        }

        productDao.getTotalPrice().test {
            val emission = awaitItem()

            with(emission){
                assertThat(this)
                    .isEqualTo(productEntities.sumOf { it.price * it.amount })
            }
        }
    }
}