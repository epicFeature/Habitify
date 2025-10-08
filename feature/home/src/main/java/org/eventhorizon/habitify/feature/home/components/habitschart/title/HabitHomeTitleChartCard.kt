package org.eventhorizon.habitify.feature.home.components.habitschart.title

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.feature.home.HomeContract
import org.eventhorizon.habitify.ui.components.theme.homeChartTitleTextStyle

@Composable
fun HabitHomeTitleChartCard(
    modifier: Modifier = Modifier,
    days: List<HomeContract.HomeUiState.State.ChartDay>
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Привычки".uppercase(),
            modifier = Modifier
                .width(120.dp)
                .align(Alignment.CenterVertically)
                .padding(start = 18.dp),
            textAlign = TextAlign.Start,
            style = homeChartTitleTextStyle
        )
        Spacer(Modifier.size(1.dp))
        DateHomeLineCard(
            modifier = Modifier,
            days = days
        )
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
private fun PreviewHabitHomeTitleChartCard() {
    val previewDays = listOf(
        HomeContract.HomeUiState.State.ChartDay("01", "ПН", false),
        HomeContract.HomeUiState.State.ChartDay("02", "ВТ", false),
        HomeContract.HomeUiState.State.ChartDay("03", "СР", false),
        HomeContract.HomeUiState.State.ChartDay("04", "ЧТ", false),
        HomeContract.HomeUiState.State.ChartDay("05", "ПТ", true),
    )
    HabitHomeTitleChartCard(
        days = previewDays
    )
}