package com.artemissoftware.shoppingcart.di

import android.content.Context
import androidx.room.Room
import com.artemissoftware.shoppingcart.data.database.ShoppingCartDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideShoppingCartDataBase(
        @ApplicationContext context: Context,
    ): ShoppingCartDatabase {
        return Room
            .databaseBuilder(
                context,
                ShoppingCartDatabase::class.java,
                "shopping_db",
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideShoppingDao(database: ShoppingCartDatabase) = database.shoppingDao()
}
