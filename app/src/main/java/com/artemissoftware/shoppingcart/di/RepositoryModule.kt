package com.artemissoftware.shoppingcart.di

import com.artemissoftware.shoppingcart.data.database.ShoppingCartDatabase
import com.artemissoftware.shoppingcart.data.database.ShoppingDao
import com.artemissoftware.shoppingcart.data.remote.PixabayApi
import com.artemissoftware.shoppingcart.data.repositories.ShoppingCartRepositoryImpl
import com.artemissoftware.shoppingcart.domain.repositories.ShoppingCartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideShoppingCartRepository(database: ShoppingCartDatabase/*, pixabayApi: PixabayApi*/): ShoppingCartRepository = ShoppingCartRepositoryImpl(database.shoppingDao())
}
