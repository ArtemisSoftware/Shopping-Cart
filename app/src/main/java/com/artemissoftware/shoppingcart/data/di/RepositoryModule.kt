package com.artemissoftware.shoppingcart.data.di

import com.artemissoftware.shoppingcart.data.database.dao.ProductDao
import com.artemissoftware.shoppingcart.data.network.source.PixabayApiSource
import com.artemissoftware.shoppingcart.data.repository.CartRepositoryImpl
import com.artemissoftware.shoppingcart.domain.repository.CartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCartRepository(productDao: ProductDao, pixabayApiSource: PixabayApiSource): CartRepository {
        return CartRepositoryImpl(productDao = productDao, pixabayApiSource = pixabayApiSource)
    }
}