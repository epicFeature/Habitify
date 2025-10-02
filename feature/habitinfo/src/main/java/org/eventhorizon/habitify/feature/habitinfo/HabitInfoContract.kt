package org.eventhorizon.habitify.feature.habitinfo

import org.eventhorizon.habitify.domain.model.database.Habit

interface HabitInfoContract {
    sealed interface HabitInfoUiEvent{

    }

    sealed interface HabitInfoUiState{
        data class State(
            val isLoading: Boolean = false,
            val habit: Habit,
            val error: String? = null
        )
    }
}