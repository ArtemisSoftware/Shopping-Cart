package com.artemissoftware.shoppingcart.data.database.di

import com.artemissoftware.shoppingcart.data.network.di.NetworkModule
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class],
)
class TestNetworkModule : NetworkModule() {
    override fun baseUrl(): String {
        return "http://localhost:8080/"
    }

    override fun addInterceptor() = false
}