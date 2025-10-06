package org.eventhorizon.habitify.feature.newhabit

import org.eventhorizon.habitify.feature.newhabit.model.HabitUi

interface NewHabitContract {
    sealed interface NewHabitUiEvent {
        data class OnHabitNameChanged(val name: String) : NewHabitUiEvent
        data class OnDurationChanged(val days: Int) : NewHabitUiEvent
        object OnDoneClick: NewHabitUiEvent
        object OnDeleteClick: NewHabitUiEvent
        object ResetAndPrepareState : NewHabitUiEvent
    }

    sealed interface NewHabitUiState {
        data class State(
            val habit: HabitUi = HabitUi(),
            val appearanceDays: List<Boolean> = emptyList(),
            val isDoneButtonEnabled: Boolean = false
        ): NewHabitUiState
    }

    sealed interface NewHabitEffect {
        object NavigateToHome : NewHabitEffect
    }
}