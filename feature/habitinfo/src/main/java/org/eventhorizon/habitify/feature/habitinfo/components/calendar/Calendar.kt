package org.eventhorizon.habitify.feature.habitinfo.components.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.Shapes
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    markedDates: Set<LocalDate>, // Даты с отметками isDone = true
    habitColor: Color            // Цвет для закрашивания отметок
) {
    // 2. Состояние для текущего отображаемого месяца.
    // Оно "живет" только внутри этого Composable.
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }

    // 3. Вся логика генерации дней для сетки
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstOfMonth = currentMonth.atDay(1)
    // Корректируем для недели, начинающейся с понедельника
    val firstDayOfWeek = firstOfMonth.dayOfWeek.value // 1=Mon, 7=Sun
    val daysFromPrevMonth = (firstDayOfWeek - 1)

    val totalCells = 35 // 5 строк * 7 дней для консистентного вида
    val days = remember(currentMonth) { // Пересчитываем только при смене месяца
        (0 until totalCells).map { i ->
            when {
                i < daysFromPrevMonth -> firstOfMonth.minusDays((daysFromPrevMonth - i).toLong())
                i >= daysFromPrevMonth + daysInMonth -> firstOfMonth.plusDays((i - daysFromPrevMonth).toLong())
                else -> firstOfMonth.plusDays((i - daysFromPrevMonth).toLong())
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(AppColor.OnbBgWhite, Shapes.medium)
            .padding(horizontal = 2.dp)
    ) {
        CalendarMonthHeader(
            modifier = Modifier.fillMaxWidth(),
            monthTitle = currentMonth.format(
                DateTimeFormatter.ofPattern(
                    "MMMM yyyy",
                    java.util.Locale.getDefault()
                )
            ),
            onPreviousMonthClick = { currentMonth = currentMonth.minusMonths(1) },
            onNextMonthClick = { currentMonth = currentMonth.plusMonths(1) }
        )
        Spacer(Modifier.size(16.dp))
        CalendarDayOfWeekHeader(modifier = Modifier.padding(horizontal = 3.dp)) //todo скорректировать на русский
        Spacer(Modifier.size(8.dp))
        // 5. Передаем подготовленные данные в CalendarDaysGrid
        CalendarDaysGrid(
            modifier = Modifier.padding(horizontal = 2.dp),
            days = days,
            currentMonth = currentMonth,
            markedDates = markedDates,
            habitColor = habitColor
        )
        Spacer(Modifier.size(12.dp))
    }
}


@Preview(showBackground = true)
@Composable
private fun CalendarPreview() {
    // 1. Создаем тестовые данные (только то, что требуется)
    val previewMarkedDates = setOf(
        LocalDate.now().minusDays(1),
        LocalDate.now().minusDays(5),
        LocalDate.now().plusDays(2)
    )

    // 2. Вызываем компонент с тестовыми данными
    // Помещаем в Column с фоном, чтобы было лучше видно
    Column(modifier = Modifier.background(AppColor.BgColorLightOrange).padding(16.dp)) {
        Calendar(
            markedDates = previewMarkedDates,
            habitColor = AppColor.habitIconColorRed // Используем другой цвет для наглядности
        )
    }
}