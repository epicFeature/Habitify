package org.eventhorizon.habitify.feature.home.components.habitschart

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.feature.home.components.habitschart.body.HabitHomeBodyChartCard
import org.eventhorizon.habitify.feature.home.components.habitschart.title.HabitHomeTitleChartCard

@Composable
fun HabitHomeChart(modifier: Modifier = Modifier, onHabitClick: (String)->Unit) {
    val scrollState = rememberScrollState()

    Column(modifier = modifier.horizontalScroll(scrollState).padding(horizontal = 20.dp)) {
        HabitHomeTitleChartCard(title = "habits")
        Spacer(Modifier.size(14.dp))
        HabitHomeBodyChartCard(onHabitClick = onHabitClick)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHabitHomeChart() {
    HabitHomeChart(onHabitClick={})
}