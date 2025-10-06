package org.eventhorizon.habitify.feature.home.components.habitschart.body

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.feature.home.HomeContract
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.Shapes
import org.eventhorizon.habitify.ui.components.theme.homeChartHabitTitleTextStyle
import java.time.LocalDate

@Composable
fun HabitHomeCard(
    modifier: Modifier = Modifier,
    habit: HomeContract.HomeUiState.State.HabitForChart,
    onCardClick: (Int) -> Unit,
    onCheckClick: (Int, LocalDate, Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .background(AppColor.White, Shapes.medium)
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable(onClick = { onCardClick(habit.id) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = habit.name.uppercase(),
            modifier = Modifier
                .padding(18.dp)
                .width(84.dp)
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            style = homeChartHabitTitleTextStyle
        )
        VerticalDivider(
            color = AppColor.BgColorLightOrange,
            thickness = 1.dp,
            modifier = Modifier.height(74.dp)
        )
        HabitHomeLineCard(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.CenterVertically),
            color = Color(habit.color),
            checks = habit.checks,
            onCheckClick = { date, isChecked ->
                onCheckClick(habit.id, date, isChecked)
            }
        )
    }
}

@Preview(showBackground = true, widthDp = 500)
@Composable
private fun PreviewHabitHomeCard() {
    val today = LocalDate.now()
    val previewHabit = HomeContract.HomeUiState.State.HabitForChart(
        id = 2,
        name = "Читать 30 мин",
        color = AppColor.habitIconColorYellow.toArgb(),
        checks = (0..4L).map {
            HomeContract.HomeUiState.State.HabitCheck(
                today.minusDays(4 - it),
                isChecked = (it % 2).toInt() != 0,
                isClickable = it == 4L)
        }
    )

    HabitHomeCard(
        habit = previewHabit,
        onCardClick = {},
        onCheckClick = { _, _, _ -> }
    )
}
