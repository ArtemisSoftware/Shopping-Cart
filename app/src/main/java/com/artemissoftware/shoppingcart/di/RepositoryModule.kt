package com.artemissoftware.shoppingcart.di

import com.artemissoftware.shoppingcart.data.database.ShoppingDao
import com.artemissoftware.shoppingcart.data.remote.PixabayApi
import com.artemissoftware.shoppingcart.data.repositories.ShoppingCartRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideShoppingCartRepository(shoppingDao: ShoppingDao, pixabayApi: PixabayApi) = ShoppingCartRepositoryImpl(shoppingDao)
}
