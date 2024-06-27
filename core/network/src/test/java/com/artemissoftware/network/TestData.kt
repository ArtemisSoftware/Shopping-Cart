package com.artemissoftware.network

import com.artemissoftware.network.dto.HitDto
import com.artemissoftware.network.dto.ImagesDto

internal object TestData {

    val productName = "my product"
    private val likes = 119
    val productPrice = likes.toDouble()
    val tags = "egg, eggs, dinosaur"
    val previewURL = "https://cdn.pixabay.com/photo/2015/12/26/17/08/egg-1108880_150.jpg"
    val comments = "my comment"

    val hitDto = HitDto(
        id = 1108880,
        pageURL = "https://pixabay.com/photos/egg-eggs-dinosaur-dragon-nest-1108880/",
        type = "photo",
        tags = tags,
        previewURL = previewURL,
        previewWidth = 150,
        previewHeight = 116,
        webFormatURL = "https://pixabay.com/get/g3663db092f5985eec5bd4363359a0077e9bdca3e12965b1455b953b4d99ff3611c68276c23f77bfccd2857edeca745c24ea79da88cea3bab35390cecc1c45ff7_640.jpg",
        webFormatWidth = 640,
        webFormatHeight = 496,
        largeImageURL = "https://pixabay.com/get/g13242d02ac027f6e950bebeba244a817bc6e36414758ec59427ba2ca332bbf5f264a534925d65de58ae1b88ca1878f3757e9c461fca83bc09fc8bdfdc34605d3_1280.jpg",
        imageWidth = 2000,
        imageHeight = 1550,
        imageSize = 714497,
        views = 30047,
        downloads = 18841,
        likes = likes,
        comments = 7,
        userId = 901738,
        user = "Viergacht",
        userImageURL = "https://cdn.pixabay.com/user/2015/04/01/16-05-10-517_250x250.png",
        fullHDURL = "https://cdn.pixabay.com/user/2015/04/01/16-05-10-517_250x250.png",
    )

    val imageDto = ImagesDto(
        total = 1,
        totalHits = 1,
        hits = listOf(hitDto)
    )

    private val hit2Dto = HitDto(
        id = 3139127,
        pageURL = "https://pixabay.com/photos/desk-work-business-office-finance-3139127/",
        type = "photo",
        tags = "desk, work, business",
        previewURL = "https://cdn.pixabay.com/photo/2018/02/08/10/22/desk-3139127_150.jpg",
        previewWidth = 150,
        previewHeight = 96,
        webFormatURL = "https://pixabay.com/get/gfde2696c3847ab7a7bcf5c921a18dcc5229cc3c5b6ca6f312be72887c672a4f83db8909fd1898a0312eb295c467f273b2f451a1bed639a6a6f7528cb70c29ee1_640.jpg",
        webFormatWidth = 640,
        webFormatHeight = 412,
        largeImageURL = "https://pixabay.com/get/g97698e04effcdab731e8a1079bd72d02f1f3347799c6597ba4fb641512fc63b7623b504bd111e755a6c1bd4be330e7aad6623a048e56d67ce322e5be7dd4d097_1280.jpg",
        imageWidth = 5983,
        imageHeight = 3853,
        imageSize = 7647330,
        views = 216561,
        downloads = 119560,
        likes = 440,
        comments = 51,
        userId = 4283981,
        user = "rawpixel",
        userImageURL = "https://cdn.pixabay.com/user/2024/03/14/08-35-52-464_250x250.jpg",
        fullHDURL = "https://cdn.pixabay.com/user/2024/03/14/08-35-52-464_250x250.jpg",
    )

    val hit3Dto = HitDto(
        id = 2953719,
        pageURL = "https://pixabay.com/photos/christmas-holiday-yuletide-cookies-2953719/",
        type = "photo",
        tags = "christmas, holiday, yuletide",
        previewURL = "https://cdn.pixabay.com/photo/2017/11/16/08/35/christmas-2953719_150.jpg",
        previewWidth = 150,
        previewHeight = 100,
        webFormatURL = "https://pixabay.com/get/g1e68da951b85a14ddae0784098af55a00a40aab91eee3a76f260c84d35900be19b73a68c3649d6bf456ebd70dcc851a1a288380451fd6ff24a34106f8aef41a1_640.jpg",
        webFormatWidth = 640,
        webFormatHeight = 427,
        largeImageURL = "https://pixabay.com/get/g69274a7cd88f622903a3e40c05c4a435ac3f74866ba645842fe16d4adf26931d424525ac00f14cc683ba8122b0a1b97d5cadc1be703c0089047f6297e132b7c7_1280.jpg",
        imageWidth = 4000,
        imageHeight = 2670,
        imageSize = 3038554,
        views = 47234,
        downloads = 18841,
        likes = 157,
        comments = 6,
        userId = 4283981,
        user = "rawpixel",
        userImageURL = "https://cdn.pixabay.com/user/2024/03/14/08-35-52-464_250x250.jpg",
        fullHDURL = "https://cdn.pixabay.com/user/2024/03/14/08-35-52-464_250x250.jpg",
    )

    val multipleImagesDto = ImagesDto(
        total = 1756617,
        totalHits = 500,
        hits = listOf(hit2Dto, hit3Dto)
    )
}