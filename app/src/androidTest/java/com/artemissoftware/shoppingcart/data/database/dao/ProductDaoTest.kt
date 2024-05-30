package com.artemissoftware.shoppingcart.data.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.artemissoftware.shoppingcart.data.database.ShoppingCartDatabase
import org.junit.After
import org.junit.Before

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

    @After
    fun tearDown(){
        shoppingCartDatabase.close()
    }
}