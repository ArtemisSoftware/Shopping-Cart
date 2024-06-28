package com.artemissoftware.product.searchproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.domain.usecases.SearchProductsUseCase
import com.artemissoftware.models.SnackBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchProductViewModel @Inject constructor(
    private val searchProductsUseCase: SearchProductsUseCase
): ViewModel() {

    private val _state = MutableStateFlow(SearchProductState())
    val state = _state.asStateFlow()

    fun onEventTrigger(event: SearchProductEvent){
        when(event){
            SearchProductEvent.Search -> search()
            is SearchProductEvent.UpdateQuery -> updateQuery(event.query)
        }
    }

    private fun updateQuery(query: String) = with(_state) {
        update {
            it.copy(searchQuery = query)
        }
    }

    private fun search() = with(_state.value) {
        viewModelScope.launch {
            searchProductsUseCase(searchQuery = searchQuery)
                .onSuccess { products ->

                    val snackBarState = if(products.isEmpty()) SnackBarState.Info("No products found") else null

                    _state.update {
                        it.copy(
                            products = products,
                            snackBarState = snackBarState
                        )
                    }
                }
                .onFailure {
                    _state.update {
                        it.copy(snackBarState = SnackBarState.Info("Error"))
                    }
                }
        }
    }
}