package com.artemissoftware.database.di

import android.content.Context
import androidx.room.Room
import com.artemissoftware.database.ShoppingCartDatabase
import com.artemissoftware.database.migrations.ManualMigration
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
    fun provideShoppingCartDatabase(
        @ApplicationContext context: Context,
    ): ShoppingCartDatabase {
        return Room
            .databaseBuilder(
                context,
                ShoppingCartDatabase::class.java,
                ShoppingCartDatabase.DATABASE_NAME,
            )
            .addMigrations(*ManualMigration.ALL_MIGRATIONS)
            .build()
    }

    @Singleton
    @Provides
    fun provideProductDao(database: ShoppingCartDatabase) = database.getProductDao()
}