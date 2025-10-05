package org.eventhorizon.habitify.feature.home.components.habitschart.title

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.feature.home.HomeContract

@Composable
fun DateHomeLineCard(
    modifier: Modifier = Modifier,
    days: List<HomeContract.HomeUiState.State.ChartDay>) { //todo подумать нужно ли это динамически или тут сама проставлю
    Row(
        modifier = modifier.padding(horizontal = 14.dp),// Расстояние между карточками с датами
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        days.forEach { day ->
            DateHomeCard( // Используем DateHomeCard для каждой даты
                dayOfMonth = day.dayOfMonth,
                dayOfWeek = day.dayOfWeek,
                isToday = day.isToday
            )
        }
    }
    /*Row(modifier) {
        Spacer(Modifier.size(14.dp))
        DateHomeCard(dayOfWeekText = "Fri", dateText = "15")
        Spacer(Modifier.size(6.dp))
        DateHomeCard(dayOfWeekText = "Fri", dateText = "16")
        Spacer(Modifier.size(6.dp))
        DateHomeCard(dayOfWeekText = "Wed", dateText = "17")
        Spacer(Modifier.size(6.dp))
        DateHomeCard(dayOfWeekText = "Thu", dateText = "18")
        Spacer(Modifier.size(6.dp))
        DateHomeCard(dayOfWeekText = "Fri", dateText = "19", isToday = true) //это сегодня
    }*/
}

@Preview(showBackground = true, widthDp = 500)
@Composable
private fun PreviewDateHomeLineCard() {
    val previewDays = listOf(
        HomeContract.HomeUiState.State.ChartDay("01", "ПН", false),
        HomeContract.HomeUiState.State.ChartDay("02", "ВТ", false),
        HomeContract.HomeUiState.State.ChartDay("03", "СР", false),
        HomeContract.HomeUiState.State.ChartDay("04", "ЧТ", false),
        HomeContract.HomeUiState.State.ChartDay("05", "ПТ", true), // Сегодня
    )
    DateHomeLineCard(
        days = previewDays
    )
}