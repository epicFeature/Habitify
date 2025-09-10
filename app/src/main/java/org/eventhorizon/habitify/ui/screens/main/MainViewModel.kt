package org.eventhorizon.habitify.ui.screens.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

//@HiltViewModel
class MainViewModel (): ViewModel() {
    private val _uiState = MutableStateFlow< MainContract.MainUiState.State>(
        MainContract.MainUiState.State()
    )
    val uiState = _uiState.asStateFlow()

    init {
        //onLaunch()
        checkOnboardingFinished()
    }

    fun handleEvent(event: MainContract.MainUiEvent) {
        when (event) {
            is MainContract.MainUiEvent.OnOnboardingFinished -> onOnboardingFinished()
        }
    }

    private fun checkOnboardingFinished(){
        //проверять состояние в datastore
    }

    private fun onOnboardingFinished(){
        //сохранять новое состояние в datastore
    }
}