package com.artemissoftware.shoppingcart.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.shoppingcart.domain.models.SnackBarState
import com.artemissoftware.shoppingcart.domain.usecases.GetProductUseCase
import com.artemissoftware.shoppingcart.domain.usecases.SaveProductUseCase
import com.artemissoftware.shoppingcart.domain.usecases.ValidateDetailUseCase
import com.artemissoftware.shoppingcart.presentation.navigation.NavArguments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val saveProductUseCase: SaveProductUseCase,
    private val validateDetailUseCase: ValidateDetailUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = MutableStateFlow(DetailsState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>(NavArguments.PRODUCT_ID)?.let {
            getProduct(it)
        }
    }

    fun onTriggerEvent(event: DetailsEvent){
        when(event){
            is DetailsEvent.Save -> save(event.comment, event.promoCode)
        }
    }

    private fun getProduct(id: Int) = with(_state) {
        viewModelScope.launch {
            setLoading()
            getProductUseCase(id)
                .onSuccess { product ->
                    update {
                        it.copy(
                            product = product,
                            isLoading = false
                        )
                    }
                }
                .onFailure {
                    update {
                        it.copy(
                            snackBarState = SnackBarState.Info("Error"),
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun save(comment: String, promoCode: String) = with(_state.value) {

        validateDetailUseCase(comments = comment, promoCode = promoCode)
            .onSuccess {
                product?.let { currentProduct ->
                    currentProduct.promoCode = promoCode
                    currentProduct.comments = comment

                    viewModelScope.launch {
                        saveProductUseCase(currentProduct)
                    }
                }
            }
            .onFailure {
                _state.update {
                    it.copy(snackBarState = SnackBarState.Info("Error"))
                }
            }
    }

    private fun setLoading() = with(_state){
        update {
            it.copy(isLoading = true)
        }
    }
}