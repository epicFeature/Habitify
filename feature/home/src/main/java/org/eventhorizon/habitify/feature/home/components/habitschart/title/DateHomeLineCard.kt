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
    days: List<HomeContract.HomeUiState.State.ChartDay>
) {
    Row(
        modifier = modifier.padding(horizontal = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        days.forEach { day ->
            DateHomeCard(
                dayOfMonth = day.dayOfMonth,
                dayOfWeek = day.dayOfWeek,
                isToday = day.isToday
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 500)
@Composable
private fun PreviewDateHomeLineCard() {
    val previewDays = listOf(
        HomeContract.HomeUiState.State.ChartDay("01", "ПН", false),
        HomeContract.HomeUiState.State.ChartDay("02", "ВТ", false),
        HomeContract.HomeUiState.State.ChartDay("03", "СР", false),
        HomeContract.HomeUiState.State.ChartDay("04", "ЧТ", false),
        HomeContract.HomeUiState.State.ChartDay("05", "ПТ", true),
    )
    DateHomeLineCard(
        days = previewDays
    )
}