package org.eventhorizon.habitify.feature.home

import java.time.LocalDate

interface HomeContract {
    sealed interface HomeUiEvent {
        data class OnHabitCheckClick(val habitId: Int, val date: LocalDate, val isChecked: Boolean) : HomeUiEvent
        data class OnHabitCardClick(val habitId: Int) : HomeUiEvent
        object OnAddHabitClick : HomeUiEvent
        object OnShowCongratsDialog : HomeUiEvent
        object OnCongratulationsDialogContinue : HomeUiEvent
        object OnCongratulationsDialogCreateNew : HomeUiEvent
    }

    sealed interface HomeUiState {
        data class State(
            val isLoadingQuote: Boolean = true,
            val quoteText: String = "",
            val quoteAuthor: String = "",
            val chartDays: List<ChartDay> = emptyList(),
            val showCongratulationsDialog: Boolean = false,
            val habits: List<HabitForChart> = emptyList(),
        ) {
            data class ChartDay(
                val dayOfMonth: String, // "01"
                val dayOfWeek: String, // "ПН"
                val isToday: Boolean,
            )

            data class HabitForChart(
                val id: Int,
                val name: String,
                val color: Int,
                val checks: List<HabitCheck>
            )

            data class HabitCheck(
                val date: LocalDate,
                val isChecked: Boolean,
                val isClickable: Boolean // пока только сегодня кликабельный
            )
        }
    }

    //одноразовые события
    sealed interface HomeUiEffect {
        data class NavigateToHabitInfo(val habitId: String) : HomeUiEffect
        data object NavigateToNewHabit : HomeUiEffect
    }

}