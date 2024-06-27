package com.artemissoftware.test.util

import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

internal object FileUtil {
    fun getJsonContent(fileName: String): String {
        val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)

        val source = inputStream?.let { inputStream.source().buffer() }
            ?: throw Exception("The file <$fileName> does not exist")


        return source.readString(StandardCharsets.UTF_8)
    }
}