package com.artemissoftware.shoppingcart.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.artemissoftware.shoppingcart.data.database.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productEntity: ProductEntity)

    @Delete
    suspend fun delete(productEntity: ProductEntity)

    @Query("SELECT * FROM products WHERE id = :id")
    fun get(id: Int): ProductEntity?

    @Query("SELECT * FROM products")
    fun getAll(): Flow<List<ProductEntity>>

    @Query("SELECT SUM(price * amount) FROM products")
    fun getTotalPrice(): Flow<Double>
}