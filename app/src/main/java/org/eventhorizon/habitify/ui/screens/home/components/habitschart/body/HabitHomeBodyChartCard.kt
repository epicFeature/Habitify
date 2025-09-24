package org.eventhorizon.habitify.ui.screens.home.components.habitschart.body

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HabitHomeBodyChartCard(modifier: Modifier = Modifier, onHabitClick: ()->Unit) { //todo хардкод заменить на динамичное
    Column(modifier = modifier) {
        HabitHomeCard(onClick = onHabitClick, habitName = "quit smoking")
        Spacer(Modifier.size(6.dp))
        HabitHomeCard(onClick = onHabitClick, habitName = "early wake ups")
        Spacer(Modifier.size(6.dp))
        HabitHomeCard(onClick = onHabitClick, habitName = "do exercise")
        Spacer(Modifier.size(6.dp))
        HabitHomeCard(onClick = onHabitClick, habitName = "healthy food")
    }
}

@Preview(showBackground = true, widthDp = 500)
@Composable
private fun PreviewHabitHomeBodyChartCard() {
    HabitHomeBodyChartCard(onHabitClick={})
}