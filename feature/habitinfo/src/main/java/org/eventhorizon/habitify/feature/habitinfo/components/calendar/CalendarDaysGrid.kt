package org.eventhorizon.habitify.feature.habitinfo.components.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.components.theme.AppColor
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarDaysGrid(
    modifier: Modifier = Modifier,
    days: List<LocalDate>,
    currentMonth: YearMonth,
    markedDates: Set<LocalDate>,
    habitColor: Color
) {
    Column(modifier = modifier) {
        days.chunked(7).forEach { weekDays ->
            Row {
                weekDays.forEach { date ->
                    val isCurrentMonth = YearMonth.from(date) == currentMonth
                    val isChecked = date in markedDates
                    CalendarDay(
                        modifier = Modifier.weight(1f),
                        date = date,
                        isCurrentMonth = isCurrentMonth,
                        isChecked = isChecked,
                        color = habitColor
                    )
                }
            }
            Spacer(modifier = Modifier.size(2.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CalendarDaysGridPreview() {
    val previewCurrentMonth = YearMonth.now()
    val previewDays = (0 until 35).map {
        previewCurrentMonth.atDay(1).plusDays(it.toLong() - 5)
    }
    val previewMarkedDates = setOf(
        LocalDate.now(),
        LocalDate.now().minusDays(2),
        LocalDate.now().plusDays(3)
    )

    CalendarDaysGrid(
        modifier = Modifier.padding(16.dp),
        days = previewDays,
        currentMonth = previewCurrentMonth,
        markedDates = previewMarkedDates,
        habitColor = AppColor.habitIconColorYellow
    )
}