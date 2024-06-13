package com.artemissoftware.shoppingcart.test.dispatcher

import com.artemissoftware.shoppingcart.test.models.ServerMockResponse.MULTIPLE_IMAGE
import com.artemissoftware.shoppingcart.test.models.toMockResponse
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockServerDispatcher {
    fun successDispatcher(): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    MULTIPLE_IMAGE.path -> MULTIPLE_IMAGE.toMockResponse()
                    else -> MockResponse().setResponseCode(200).setBody("")
                }
            }
        }
    }

    fun errorDispatcher(): Dispatcher {
        return object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().setResponseCode(400).setBody("")
            }
        }
    }
}