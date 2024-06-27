package com.artemissoftware.database.di

import android.content.Context
import androidx.room.Room
import com.artemissoftware.database.ShoppingCartDatabase
import dagger.Module
import dagger.Provides
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