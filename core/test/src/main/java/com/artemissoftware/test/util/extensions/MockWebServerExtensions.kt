package com.artemissoftware.test.util.extensions

import com.artemissoftware.test.model.ServerMockResponse
import com.artemissoftware.test.util.FileUtil
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

fun MockWebServer.enqueueResponse(response: ServerMockResponse, code: Int = 200) {
    enqueue(
        MockResponse()
            .setResponseCode(code)
            .setBody(FileUtil.getJsonContent(response.file))
    )
}

fun MockWebServer.enqueueErrorResponse(code: Int = 400) {
    enqueue(
        MockResponse()
            .setResponseCode(code)
            .setBody("")
    )
}