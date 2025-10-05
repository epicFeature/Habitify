package org.eventhorizon.habitify.feature.home.components.habitschart.body

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.feature.home.HomeContract
import org.eventhorizon.habitify.ui.components.theme.AppColor
import java.time.LocalDate

@Composable
fun HabitHomeBodyChartCard(
    modifier: Modifier = Modifier,
    habits: List<HomeContract.HomeUiState.State.HabitForChart>,
    onHabitCardClick:(Int) -> Unit,
    onHabitCheckClick: (Int, LocalDate, Boolean) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        habits.forEach { habit ->
            HabitHomeCard(
                habit = habit,
                modifier = Modifier,
                onCardClick = onHabitCardClick,
                onCheckClick = onHabitCheckClick,
            )
        }
    }
//    Column(modifier = modifier) {
//        HabitHomeCard(onClick = onHabitClick, habitName = "quit smoking")
//        Spacer(Modifier.size(6.dp))
//        HabitHomeCard(onClick = onHabitClick, habitName = "early wake ups")
//        Spacer(Modifier.size(6.dp))
//        HabitHomeCard(onClick = onHabitClick, habitName = "do exercise")
//        Spacer(Modifier.size(6.dp))
//        HabitHomeCard(onClick = onHabitClick, habitName = "healthy food")
//    }
}

@Preview(showBackground = true, widthDp = 500)
@Composable
private fun PreviewHabitHomeBodyChartCard() {
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
                    isClickable = it == 4L)
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
                    isClickable = it == 4L)
            }
        )
    )
    HabitHomeBodyChartCard(
        modifier = Modifier,
        habits = previewHabits,
        onHabitCardClick = {1},
        onHabitCheckClick = { i: Int, date: LocalDate, bool: Boolean -> }
    )
}