package com.artemissoftware.shoppingcart.data.database.di

import android.content.Context
import androidx.room.Room
import com.artemissoftware.shoppingcart.data.database.ShoppingCartDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [DataBaseModule::class])
object TestDataBaseModule {

    @Singleton
    @Provides
    fun provideInMemoryDb(
        @ApplicationContext context: Context,
    ): ShoppingCartDatabase {
        return  Room.inMemoryDatabaseBuilder(context, ShoppingCartDatabase::class.java).build()
    }

    @Singleton
    @Provides
    fun provideProductDao(database: ShoppingCartDatabase) = database.getProductDao()
}