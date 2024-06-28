package com.artemissoftware.test.model

import com.artemissoftware.test.util.FileUtil.getJsonContent
import okhttp3.mockwebserver.MockResponse

enum class ServerMockResponse(val path: String, val file: String){
    SINGLE_IMAGE(path = "/api/?id=1108880", file = "image.json"),
    MULTIPLE_IMAGE(path = "/api/?q=egg", file = "multiple_images.json");

}

fun ServerMockResponse.toMockResponse(): MockResponse {
    return MockResponse()
        .setResponseCode(200)
        .setBody(getJsonContent(file))
}