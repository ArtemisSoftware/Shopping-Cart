package com.artemissoftware.shoppingcart.data

internal object ServerMockData {

    val imageResponse =
        """
                {
                "total": 1,
                "totalHits": 1,
                "hits": [
                {
                "id": 1108880,
                "pageURL": "https://pixabay.com/photos/egg-eggs-dinosaur-dragon-nest-1108880/",
                "type": "photo",
                "tags": "egg, eggs, dinosaur",
                "previewURL": "https://cdn.pixabay.com/photo/2015/12/26/17/08/egg-1108880_150.jpg",
                "previewWidth": 150,
                "previewHeight": 116,
                "webformatURL": "https://pixabay.com/get/g3663db092f5985eec5bd4363359a0077e9bdca3e12965b1455b953b4d99ff3611c68276c23f77bfccd2857edeca745c24ea79da88cea3bab35390cecc1c45ff7_640.jpg",
                "webformatWidth": 640,
                "webformatHeight": 496,
                "largeImageURL": "https://pixabay.com/get/g13242d02ac027f6e950bebeba244a817bc6e36414758ec59427ba2ca332bbf5f264a534925d65de58ae1b88ca1878f3757e9c461fca83bc09fc8bdfdc34605d3_1280.jpg",
                "imageWidth": 2000,
                "imageHeight": 1550,
                "imageSize": 714497,
                "views": 30047,
                "downloads": 18841,
                "collections": 145,
                "likes": 119,
                "comments": 7,
                "user_id": 901738,
                "user": "Viergacht",
                "userImageURL": "https://cdn.pixabay.com/user/2015/04/01/16-05-10-517_250x250.png",
                "fullHDURL": "https://cdn.pixabay.com/user/2015/04/01/16-05-10-517_250x250.png"
                }
                ]
                }
            """.trimIndent()
}