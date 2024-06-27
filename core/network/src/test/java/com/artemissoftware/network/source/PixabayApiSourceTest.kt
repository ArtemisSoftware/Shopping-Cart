package com.artemissoftware.network.source

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.network.PixabayApi
import com.artemissoftware.network.TestData.imageDto
import com.artemissoftware.network.TestData.multipleImagesDto
import com.artemissoftware.test.model.ServerMockResponse.MULTIPLE_IMAGE
import com.artemissoftware.test.model.ServerMockResponse.SINGLE_IMAGE
import com.artemissoftware.test.network.MockServerTest
import com.artemissoftware.test.util.extensions.enqueueErrorResponse
import com.artemissoftware.test.util.extensions.enqueueResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@OptIn(ExperimentalCoroutinesApi::class)
internal class PixabayApiSourceTest: MockServerTest() {

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
        mockWebServer.enqueueResponse(SINGLE_IMAGE)

        val result = pixabayApiSource.getImageById("2")

        assertThat(result)
            .isEqualTo(imageDto)
    }

    @Test
    fun `Get image by id, return error`() = runTest {
        mockWebServer.enqueueErrorResponse()

        assertFailure {
            pixabayApiSource.getImageById("2")
        }
    }

    @Test
    fun `Get images by search query`() = runBlocking {
        mockWebServer.enqueueResponse(MULTIPLE_IMAGE)

        val result = pixabayApiSource.getImages("desk")

        assertThat(result)
            .isEqualTo(multipleImagesDto)
    }

    @Test
    fun `Get images, return error`()  = runTest {
        mockWebServer.enqueueErrorResponse()

        assertFailure {
            pixabayApiSource.getImages("eggs")
        }
    }

}