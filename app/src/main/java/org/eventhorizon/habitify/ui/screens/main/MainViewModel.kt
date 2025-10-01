package org.eventhorizon.habitify.ui.screens.main

/*

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
}*/
