package com.artemissoftware.shoppingcart.presentation.addproduct

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.shoppingcart.domain.usecases.GetProductUseCase
import com.artemissoftware.shoppingcart.domain.usecases.SaveProductUseCase
import com.artemissoftware.shoppingcart.presentation.navigation.NavArguments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val saveProductUseCase: SaveProductUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = MutableStateFlow(AddProductState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<String>(NavArguments.PRODUCT_ID)?.let { id ->
            getProduct(id = id)
        }
    }

    fun onTriggerEvent(event: AddProductEvent){
        when(event){
            AddProductEvent.AddQuantity -> addQuantity()
            AddProductEvent.BuyProduct -> buyProduct()
            AddProductEvent.RemoveQuantity -> removeQuantity()
        }
    }

    private fun addQuantity() = with(_state) {
        value.product?.let { currentProduct ->
            val product = currentProduct.copy(quantity = currentProduct.quantity + 1)
            update {
                it.copy(product = product)
            }
        }
    }

    private fun removeQuantity() = with(_state) {
        value.product?.let { currentProduct ->
            if(currentProduct.quantity != 0) {
                val product = currentProduct.copy(quantity = currentProduct.quantity - 1)
                update {
                    it.copy(product = product)
                }
            }
        }
    }

    private fun getProduct(id: String) = with(_state) {
        viewModelScope.launch {
            getProductUseCase(id = id)
                .onSuccess { product ->
                    update {
                        it.copy(product = product)
                    }
                }
                .onFailure {  }
        }

        value.product?.let { currentProduct ->

        }
    }

    private fun buyProduct() = with(_state) {
        value.product?.let { currentProduct ->
            viewModelScope.launch {
                saveProductUseCase(product = currentProduct)
            }
        }
    }

}