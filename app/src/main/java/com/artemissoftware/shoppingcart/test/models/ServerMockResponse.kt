package com.artemissoftware.shoppingcart.test.models

import com.artemissoftware.shoppingcart.test.models.MockData.MULTIPLE_IMAGE_FILE
import com.artemissoftware.shoppingcart.test.util.MockWebServerUtil.getJsonContent
import okhttp3.mockwebserver.MockResponse

private object MockData {

    private const val IMAGES_FOLDER = "images/"
    const val SINGLE_IMAGE_RESPONSE = IMAGES_FOLDER + "image.json"
    const val MULTIPLE_IMAGE_FILE = IMAGES_FOLDER + "multiple_images.json"
    const val ERROR_RESPONSE = IMAGES_FOLDER + "error.json"
}

enum class ServerMockResponse(val path: String, val file: String){
    MULTIPLE_IMAGE(path = "/api/?q=egg", file = MULTIPLE_IMAGE_FILE);

}

fun ServerMockResponse.toMockResponse(): MockResponse{
    return MockResponse()
        .setResponseCode(200)
        .setBody(getJsonContent(file))
}