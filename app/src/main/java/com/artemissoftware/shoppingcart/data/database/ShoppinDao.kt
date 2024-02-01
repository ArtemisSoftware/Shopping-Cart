package com.artemissoftware.shoppingcart.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(shoppingItemEntity: ShoppingItemEntity)

    @Delete
    suspend fun deleteShoppingItem(shoppingItemEntity: ShoppingItemEntity)

    @Query("SELECT * FROM shoppingItems")
    fun getAllShoppingItems(): Flow<List<ShoppingItemEntity>>

    @Query("SELECT SUM(price * amount) FROM shoppingItems")
    fun getTotalPrice(): Flow<Float>
}
