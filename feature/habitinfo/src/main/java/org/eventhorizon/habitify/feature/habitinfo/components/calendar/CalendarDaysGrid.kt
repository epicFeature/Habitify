package org.eventhorizon.habitify.feature.habitinfo.components.calendar

import android.os.Build
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarDaysGrid(
    modifier: Modifier = Modifier,
    // 1. Принимаем простые данные вместо ViewModel
    days: List<LocalDate>,
    currentMonth: YearMonth,
    markedDates: Set<LocalDate>,
    habitColor: Color
) {
    // 3. УБИРАЕМ LazyVerticalGrid
    Column(modifier = modifier) {
        // Делим список дней на чанки по 7 (недели)
        days.chunked(7).forEach { weekDays ->
            Row {
                weekDays.forEach { date ->
                    val isCurrentMonth = YearMonth.from(date) == currentMonth
                    val isChecked = date in markedDates
                    CalendarDay(
                        modifier = Modifier.weight(1f), // Равномерно распределяем дни
                        date = date,
                        isCurrentMonth = isCurrentMonth,
                        isChecked = isChecked,
                        color = habitColor
                    )
                }
            }
            Spacer(modifier= Modifier.size(2.dp))
        }
    }

/*    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(7)
    ) {
        items(days.size) { index ->
            val date = days[index]
            // 2. Логика isCurrentMonth и isChecked теперь определяется здесь
            val isCurrentMonth = YearMonth.from(date) == currentMonth
            val isChecked = date in markedDates

            // 3. Передаем данные в CalendarDay
            CalendarDay(
                date = date,
                isCurrentMonth = isCurrentMonth,
                isChecked = isChecked,
                modifier = Modifier.padding(horizontal = 3.dp, vertical = 4.dp),
                color = habitColor
            )
        }
    }*/
}


@Preview(showBackground = true)
@Composable
private fun CalendarDaysGridPreview() {
    // 1. Создаем тестовые данные
    val previewCurrentMonth = YearMonth.now()
    val previewDays = (0 until 35).map {
        previewCurrentMonth.atDay(1).plusDays(it.toLong() - 5) // Немного дней из прошлого и будущего месяцев
    }
    val previewMarkedDates = setOf(
        LocalDate.now(),
        LocalDate.now().minusDays(2),
        LocalDate.now().plusDays(3)
    )

    // 2. Вызываем компонент с тестовыми данными
    CalendarDaysGrid(
        modifier = Modifier.padding(16.dp),
        days = previewDays,
        currentMonth = previewCurrentMonth,
        markedDates = previewMarkedDates,
        habitColor = AppColor.habitIconColorYellow // Используем какой-нибудь цвет из вашей палитры
    )
}