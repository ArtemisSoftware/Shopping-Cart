package com.artemissoftware.shoppingcart.di

import android.content.Context
import androidx.room.Room
import com.artemissoftware.shoppingcart.data.database.ShoppingCartDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Named
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [DataBaseModule::class])
object TestAppModule {

    @Provides
    fun provideInMemoryDb(@ApplicationContext context: Context): ShoppingCartDatabase =
        Room.inMemoryDatabaseBuilder(context, ShoppingCartDatabase::class.java)
            .allowMainThreadQueries()
            .build()

//    @Singleton
//    @Provides
//    fun provideShoppingDao(database: ShoppingCartDatabase) = database.shoppingDao()
}
