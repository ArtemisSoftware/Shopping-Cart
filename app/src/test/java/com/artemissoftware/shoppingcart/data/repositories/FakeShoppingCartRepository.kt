package com.artemissoftware.shoppingcart.data.repositories

import com.artemissoftware.shoppingcart.domain.models.ShoppingItem
import com.artemissoftware.shoppingcart.domain.repositories.ShoppingCartRepository

class FakeShoppingCartRepository : ShoppingCartRepository {

    private val shoppingItems = mutableListOf<ShoppingItem>()

//    private val observableShoppingItems = MutableLiveData<List<ShoppingItem>>(shoppingItems)
//    private val observableTotalPrice = MutableLiveData<Float>()

    var shouldReturnNetworkError = false

    private fun getTotalPrice(): Float {
        return shoppingItems.sumOf { it.price.toDouble() }.toFloat()
    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
//        refreshLiveData()
    }

//    private fun refreshLiveData() {
//        observableShoppingItems.postValue(shoppingItems)
//        observableTotalPrice.postValue(getTotalPrice())
//    }

//
//    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
//        shoppingItems.remove(shoppingItem)
//        refreshLiveData()
//    }
//
//    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
//        return observableShoppingItems
//    }
//
//    override fun observeTotalPrice(): LiveData<Float> {
//        return observableTotalPrice
//    }
//
//    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
//        return if(shouldReturnNetworkError) {
//            Resource.error("Error", null)
//        } else {
//            Resource.success(ImageResponse(listOf(), 0, 0))
//        }
//    }
}
