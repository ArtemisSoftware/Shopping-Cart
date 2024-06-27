package com.artemissoftware.test.model

enum class ServerMockResponse(val path: String, val file: String){
    SINGLE_IMAGE(path = "/api/?id=1108880", file = "image.json"),
    MULTIPLE_IMAGE(path = "/api/?q=egg", file = "multiple_images.json");

}