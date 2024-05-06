package com.artemissoftware.shoppingcart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ExampleViewModel : ViewModel() {
    var isJobDone = false
        private set

    fun startJob() {
        viewModelScope.launch {
            // Simulate a background job
            kotlinx.coroutines.delay(100)
            isJobDone = true
        }
    }
}