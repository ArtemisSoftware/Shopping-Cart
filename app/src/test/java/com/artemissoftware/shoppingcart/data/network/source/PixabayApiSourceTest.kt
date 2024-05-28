package com.artemissoftware.shoppingcart.data.network.source

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.shoppingcart.ProductTestData.imageDto
import com.artemissoftware.shoppingcart.ProductTestData.multipleImagesDto
import com.artemissoftware.shoppingcart.data.ServerMockData
import com.artemissoftware.shoppingcart.data.ServerMockData.ERROR_RESPONSE
import com.artemissoftware.shoppingcart.data.ServerMockData.MULTIPLE_IMAGE_RESPONSE
import com.artemissoftware.shoppingcart.data.ServerMockData.SINGLE_IMAGE_RESPONSE
import com.artemissoftware.shoppingcart.data.network.PixabayApi
import com.artemissoftware.shoppingcart.test.util.extensions.enqueueResponse
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
    fun `Get image by id`() = runBlocking {
        mockWebServer.enqueueResponse(SINGLE_IMAGE_RESPONSE)

        val result = pixabayApiSource.getImageById("2")

        assertThat(result)
            .isEqualTo(imageDto)
    }

    @Test
    fun `Get image by id, return error`() = runTest {
        mockWebServer.enqueueResponse(ERROR_RESPONSE, 400)

        assertFailure {
            pixabayApiSource.getImageById("2")
        }
    }

    @Test
    fun `Get images by search query`() = runBlocking {
        mockWebServer.enqueueResponse(MULTIPLE_IMAGE_RESPONSE)

        val result = pixabayApiSource.getImages("desk")

        assertThat(result)
            .isEqualTo(multipleImagesDto)
    }

    @Test
    fun `Get images, return error`()  = runTest {
        mockWebServer.enqueueResponse(ERROR_RESPONSE, 400)

        assertFailure {
            pixabayApiSource.getImageById("eggs")
        }
    }
}