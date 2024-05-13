package com.artemissoftware.shoppingcart.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.shoppingcart.domain.usecases.GetCartUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel constructor(
    private val getCartUseCase: GetCartUseCase
): ViewModel() {

    private val _state = MutableStateFlow(CartState())
    val state = _state.asStateFlow()

    init {
        getCart()
    }

    private fun getCart() = with(_state){
        viewModelScope.launch {
            getCartUseCase().collect{ result ->
                update {
                    it.copy(products = result)
                }
            }
        }
    }
}