package com.artemissoftware.shoppingcart.data.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.artemissoftware.shoppingcart.InstrumentedProductTestData.productEntities
import com.artemissoftware.shoppingcart.InstrumentedProductTestData.productEntity
import com.artemissoftware.shoppingcart.InstrumentedProductTestData.productEntity2
import com.artemissoftware.shoppingcart.data.database.ShoppingCartDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class ProductDaoTest {

    private lateinit var shoppingCartDatabase: ShoppingCartDatabase
    private lateinit var productDao: ProductDao

    @Before
    fun setUp(){
        shoppingCartDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingCartDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        productDao = shoppingCartDatabase.getProductDao()
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
    fun `Get all products`() = runBlocking {
        productEntities.forEach { entity ->
            productDao.insert(productEntity = entity)
        }

        val result = productDao.getAll().first()

        assertThat(result)
            .isEqualTo(productEntities)
    }

    @Test
    fun `Get total price`() = runBlocking {
        productEntities.forEach { entity ->
            productDao.insert(productEntity = entity)
        }

        val result = productDao.getTotalPrice().first()

        assertThat(result)
            .isEqualTo(productEntities.sumOf { it.price * it.amount })
    }

    @After
    fun tearDown(){
        shoppingCartDatabase.close()
    }
}