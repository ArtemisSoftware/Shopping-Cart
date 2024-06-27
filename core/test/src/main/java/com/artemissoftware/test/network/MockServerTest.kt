package com.artemissoftware.test.network

import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer

abstract class MockServerTest {

    protected val mockWebServer = MockWebServer()
    protected val client = OkHttpClient.Builder().build()
}