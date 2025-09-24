package org.eventhorizon.habitify.ui.screens.habitinfo.components.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarViewModel : ViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    private var currentMonth: YearMonth = YearMonth.now()

    // Данные статистики (пример: дни с выполнением привычки)
    @RequiresApi(Build.VERSION_CODES.O)
    val habitData: Map<LocalDate, Boolean> = mapOf(
        LocalDate.now().minusDays(1) to true,
        LocalDate.now().minusDays(3) to false
    )

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMonthTitle(): String {
        return currentMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy"))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDays(): List<LocalDate> {
        val daysInMonth = currentMonth.lengthOfMonth()
        val firstDay = currentMonth.atDay(1)
        val offset = firstDay.dayOfWeek.value - DayOfWeek.MONDAY.value

        return List(42) { index ->
            if (index < offset) {
                firstDay.minusDays((offset - index).toLong())
            } else if (index >= daysInMonth + offset) {
                firstDay.plusDays((index - offset - daysInMonth + 1).toLong())
            } else {
                firstDay.plusDays((index - offset).toLong())
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun navigateToPreviousMonth() {
        currentMonth = currentMonth.minusMonths(1)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun navigateToNextMonth() {
        currentMonth = currentMonth.plusMonths(1)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isCurrentMonth(date: LocalDate): Boolean {
        return YearMonth.from(date) == currentMonth
    }
}