package org.eventhorizon.habitify.feature.home.components.habitschart

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.feature.home.HomeContract
import org.eventhorizon.habitify.feature.home.components.habitschart.body.HabitHomeBodyChartCard
import org.eventhorizon.habitify.feature.home.components.habitschart.title.HabitHomeTitleChartCard
import org.eventhorizon.habitify.ui.components.theme.AppColor
import java.time.LocalDate

@Composable
fun HabitHomeChart(
    modifier: Modifier = Modifier,
    chartDays: List<HomeContract.HomeUiState.State.ChartDay>,
    habits: List<HomeContract.HomeUiState.State.HabitForChart>, //todo вынести класс за пределы state для ui
    onHabitCardClick: (Int) -> Unit,
    onHabitCheckClick: (Int, LocalDate, Boolean) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .horizontalScroll(scrollState)
            .padding(horizontal = 20.dp)
    ) {
        HabitHomeTitleChartCard(days = chartDays)
        Spacer(Modifier.size(14.dp))
        HabitHomeBodyChartCard(
            habits = habits,
            onHabitCardClick = onHabitCardClick,
            onHabitCheckClick = onHabitCheckClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHabitHomeChart() {
    val today = LocalDate.now()
    val previewHabits = listOf(
        HomeContract.HomeUiState.State.HabitForChart(
            id = 1,
            name = "Пробежка",
            color = AppColor.habitIconColorBlue.toArgb(),
            checks = (0..4L).map {
                HomeContract.HomeUiState.State.HabitCheck(
                    today.minusDays(4 - it),
                    isChecked = (it % 2).toInt() == 0,
                    isClickable = it == 4L
                )
            }
        ),
        HomeContract.HomeUiState.State.HabitForChart(
            id = 2,
            name = "Читать 30 мин",
            color = AppColor.habitIconColorYellow.toArgb(),
            checks = (0..4L).map {
                HomeContract.HomeUiState.State.HabitCheck(
                    today.minusDays(4 - it),
                    isChecked = (it % 2).toInt() != 0,
                    isClickable = it == 4L
                )
            }
        ),
        HomeContract.HomeUiState.State.HabitForChart(
            id = 3,
            name = "Медитация",
            color = AppColor.habitIconColorPurple.toArgb(),
            checks = (0..4L).map {
                HomeContract.HomeUiState.State.HabitCheck(
                    today.minusDays(4 - it),
                    isChecked = true,
                    isClickable = it == 4L
                )
            }
        )
    )

    HabitHomeChart(
        modifier = Modifier,
        chartDays = listOf(
            HomeContract.HomeUiState.State.ChartDay(
                dayOfMonth = "1",
                dayOfWeek = "пн",
                isToday = false
            ),
            HomeContract.HomeUiState.State.ChartDay(
                dayOfMonth = "2",
                dayOfWeek = "вт",
                isToday = false
            ),
            HomeContract.HomeUiState.State.ChartDay(
                dayOfMonth = "3",
                dayOfWeek = "ср",
                isToday = false
            ),
            HomeContract.HomeUiState.State.ChartDay(
                dayOfMonth = "4",
                dayOfWeek = "чт",
                isToday = false
            ),
            HomeContract.HomeUiState.State.ChartDay(
                dayOfMonth = "5",
                dayOfWeek = "пт",
                isToday = true
            ),
        ),
        habits = previewHabits,
        onHabitCardClick = { 1 },
        onHabitCheckClick = { i: Int, date: LocalDate, bool: Boolean -> }
    )
}