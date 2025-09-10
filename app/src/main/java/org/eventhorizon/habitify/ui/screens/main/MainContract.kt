package org.eventhorizon.habitify.ui.screens.main


interface MainContract {
    sealed interface MainUiEvent {
        object OnOnboardingFinished : MainUiEvent
    }

    sealed interface MainUiState {
        data class State(
            val isLoading: Boolean = false,
            val onboardingFinished: Boolean = true,
            val error: String? = null
        ) : MainUiState
    }
}