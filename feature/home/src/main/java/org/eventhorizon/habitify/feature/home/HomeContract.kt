package org.eventhorizon.habitify.feature.home

import org.eventhorizon.habitify.domain.model.database.Habit

interface HomeContract {

    sealed interface HomeUiEvent {
        data class OnHabitCardClick(val habitId: Long) : HomeUiEvent //примерно
    }

    sealed interface HomeUiState {
        data class State(
            val isLoading: Boolean = false,
            val habits: List<Habit> = emptyList(),
            val error: String? = null
        )
    }


}