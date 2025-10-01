package org.eventhorizon.habitify.feature.home.components.habitschart.body

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.components.theme.AppColor

@Composable
fun HabitHomeLineCard(modifier: Modifier = Modifier, color: Color) {
    Row(modifier) {
        Spacer(Modifier.size(14.dp))
        HabitCheckCard(color = color, isChecked = true)
        Spacer(Modifier.size(2.dp))
        HabitCheckCard(color = color, isChecked = true)
        Spacer(Modifier.size(2.dp))
        HabitCheckCard(color = color, isChecked = false)
        Spacer(Modifier.size(2.dp))
        HabitCheckCard(color = color, isChecked = true)
        Spacer(Modifier.size(2.dp))
        HabitCheckCard(color = color, isChecked = false) //это сегодня
        Spacer(Modifier.size(14.dp))
    }
}

@Preview(showBackground = true, widthDp = 500)
@Composable
private fun PreviewHabitHomeLineCard() {
    HabitHomeLineCard(color = AppColor.habitIconColorBlue)
}