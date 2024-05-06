package com.artemissoftware.shoppingcart

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)  // Set the main dispatcher to the test dispatcher
    }


    @AfterEach
    fun teardown() {
        Dispatchers.resetMain()  // Reset after each test to avoid side effects
    }

    @Test
    fun testViewModelScopeLaunch() = runTest {
        val viewModel = ExampleViewModel()

        viewModel.startJob()  // Start the coroutine in viewModelScope

        // Advance time to simulate the delay
        testDispatcher.scheduler.advanceUntilIdle()

        // Check that the job completed as expected
        Assertions.assertTrue(viewModel.isJobDone)  // Assertion to ensure the coroutine did its work
    }
}