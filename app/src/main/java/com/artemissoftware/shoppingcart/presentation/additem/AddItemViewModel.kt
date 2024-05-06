package com.artemissoftware.shoppingcart.presentation.additem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.shoppingcart.domain.models.ShoppingItem
import com.artemissoftware.shoppingcart.domain.usecases.InsertShoppingItemUseCase
import com.artemissoftware.shoppingcart.domain.usecases.ValidateItemUseCase
import com.artemissoftware.shoppingcart.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val validateItemUseCase: ValidateItemUseCase,
    private val insertShoppingItemUseCase: InsertShoppingItemUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(AddItemState())
    val state: StateFlow<AddItemState> = _state.asStateFlow()

    fun onTriggerEvent(event: AddItemEvents) {
        when (event) {
            AddItemEvents.InsertItem -> insertItem()
            is AddItemEvents.UpdateAmount -> updateAmount(event.amount)
            is AddItemEvents.UpdateImageUrl -> updateImageUrl(event.imageUrl)
            is AddItemEvents.UpdateName -> updateName(event.name)
            is AddItemEvents.UpdatePrice -> updatePrice(event.price)
        }
    }

    private fun updateAmount(amount: String) = with(_state) {
        update {
            it.copy(amount = amount)
        }
    }

    private fun updateName(name: String) = with(_state) {
        update {
            it.copy(name = name)
        }
    }

    private fun updatePrice(price: String) = with(_state) {
        update {
            it.copy(price = price)
        }
    }

    private fun updateImageUrl(imageUrl: String) = with(_state) {
        update {
            it.copy(imageUrl = imageUrl)
        }
    }

    private fun insertItem() = with(_state) {
        val result = validateItemUseCase(amount = value.amount, price = value.price, name = value.name)

        when (result) {
            is Resource.Success -> {
                viewModelScope.launch {
                    val shoppingItem = ShoppingItem(value.name, value.amount.toInt(), value.price.toFloat(), value.imageUrl)
                    insertShoppingItemUseCase(shoppingItem = shoppingItem)
                }
            }
            is Resource.Error -> {
                update {
                    it.copy(error = result.msg) // TODO: mudar isto
                }
            }
            else -> Unit
        }
    }
}
