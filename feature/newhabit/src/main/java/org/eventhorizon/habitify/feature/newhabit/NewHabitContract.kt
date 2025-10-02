package org.eventhorizon.habitify.feature.newhabit

import org.eventhorizon.habitify.feature.newhabit.model.HabitUi

interface NewHabitContract {
    sealed interface NewHabitUiEvent {
        data class OnHabitNameChanged(val name: String) : NewHabitUiEvent
        data class OnDurationChanged(val days: Int) : NewHabitUiEvent
        object OnDoneClick: NewHabitUiEvent
        object OnDeleteClick: NewHabitUiEvent
    }

    sealed interface NewHabitUiState {
        data class State(
            // Теперь все данные о привычке инкапсулированы в одном объекте
            val habit: HabitUi = HabitUi(),
            val appearanceDays: List<Boolean> = emptyList(),
            val isDoneButtonEnabled: Boolean = false
        )
    }

    /**
     * Одноразовые эффекты, которые ViewModel отправляет в UI.
     */
    sealed interface NewHabitEffect {
        /** Эффект для навигации на главный экран. */
        object NavigateToHome : NewHabitEffect
    }
}