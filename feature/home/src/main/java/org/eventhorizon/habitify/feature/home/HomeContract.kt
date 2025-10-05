package org.eventhorizon.habitify.feature.home

import java.time.LocalDate

interface HomeContract {


    // 2. Event: Все действия, которые пользователь может совершить на экране
    sealed interface HomeUiEvent {
        // Пользователь нажал на чекбокс привычки для определенной даты
        data class OnHabitCheckClick(val habitId: Int, val date: LocalDate, val isChecked: Boolean) : HomeUiEvent
        // Пользователь нажал на карточку привычки для перехода к деталям
        data class OnHabitCardClick(val habitId: Int) : HomeUiEvent
        // Пользователь нажал на кнопку "Добавить привычку"
        data object OnAddHabitClick : HomeUiEvent

        object OnCongratulationsDialogContinue : HomeUiEvent
        object OnCongratulationsDialogCreateNew : HomeUiEvent
    }

    sealed interface HomeUiState {
        // 1. State: Описывает состояние экрана в любой момент времени
        data class State(
            val isLoadingQuote: Boolean = true,
            val quoteText: String = "",
            val quoteAuthor: String = "",
            val chartDays: List<ChartDay> = emptyList(),
            val showCongratulationsDialog: Boolean = false,
            val habits: List<HabitForChart> = emptyList(),
        ) {
            // Вспомогательные классы для UI, чтобы не тащить доменные модели напрямую в View
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
                val isClickable: Boolean // Только сегодня можно кликать
            )
        }
    }

    // 3. Effect: Одноразовые события, которые ViewModel отправляет View (например, навигация)
    sealed interface HomeUiEffect {
        data class NavigateToHabitInfo(val habitId: String) : HomeUiEffect
        data object NavigateToNewHabit : HomeUiEffect
    }

}