package org.eventhorizon.habitify.feature.habitinfo

import androidx.compose.ui.graphics.Color
import org.eventhorizon.habitify.ui.components.theme.AppColor
import java.time.LocalDate

interface HabitInfoContract {
    sealed interface HabitInfoEvent {
        data object OnCompleteClick : HabitInfoEvent
        data object OnDeleteClick : HabitInfoEvent
    }

    sealed interface HabitInfoState {
        data class State(
            val habitName: String = "",
            val daysLeft: Int = 0,
            val markedDates: Set<LocalDate> = emptySet(),
            val habitColor: Color = AppColor.OnbBtnOrange,
            val isLoading: Boolean = true
        ) : HabitInfoState
    }

    sealed interface HabitInfoEffect {
        data object NavigateBackToHome : HabitInfoEffect
        data class NavigateBackWithCompletion(val showCongrats: Boolean) : HabitInfoEffect
    }
}