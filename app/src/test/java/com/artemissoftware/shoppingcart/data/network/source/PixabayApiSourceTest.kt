package com.artemissoftware.shoppingcart.data.network.source

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.shoppingcart.ProductTestData.imageDto
import com.artemissoftware.shoppingcart.data.ServerMockData
import com.artemissoftware.shoppingcart.data.network.PixabayApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


internal class PixabayApiSourceTest{

    private val mockWebServer = MockWebServer()
    private val client = OkHttpClient.Builder().build()
    private lateinit var pixabayApi: PixabayApi
    private lateinit var pixabayApiSource: PixabayApiSource

    @BeforeEach
    fun setUp(){
        pixabayApi =  Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PixabayApi::class.java)

        pixabayApiSource = PixabayApiSource(pixabayApi = pixabayApi)
    }

    @AfterEach
    fun tearDown(){
        mockWebServer.shutdown()
    }

    @Test
    fun `Get image by id`() = runBlocking{

        val response = MockResponse()
            .setResponseCode(200)
            .setBody(ServerMockData.imageResponse)

        mockWebServer.enqueue(response)

        val result = pixabayApiSource.getImageById("2")

        assertThat(result)
            .isEqualTo(imageDto)
    }

    @Test
    fun `Get image by id, return error`()  = runTest {
        val response = MockResponse()
            .setResponseCode(400)
            .setBody("[ERROR 400] Invalid value for \"id\".")

        mockWebServer.enqueue(response)

        assertFailure {
            pixabayApiSource.getImageById("2")
        }
    }

    @Test
    fun `Get images`() = runBlocking{

        val response = MockResponse()
            .setResponseCode(200)
            .setBody(ServerMockData.imageResponse)

        mockWebServer.enqueue(response)

        val result = pixabayApiSource.getImages("eggs")

        assertThat(result)
            .isEqualTo(imageDto)
    }

    @Test
    fun `Get images, return error`()  = runTest {
        val response = MockResponse()
            .setResponseCode(400)
            .setBody("[ERROR 400] Invalid value for \"id\".")

        mockWebServer.enqueue(response)

        assertFailure {
            pixabayApiSource.getImageById("eggs")
        }
    }
}