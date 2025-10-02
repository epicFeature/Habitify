package org.eventhorizon.habitify.feature.newhabit.components.topcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.Shapes

@Composable
fun HabitNameCard(
    modifier: Modifier,
    habitName: String,
    habitColor: Color,
    onHabitNameChanged: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        HabitNameTextField(
            Modifier.weight(1F),
            habitName = habitName,
            onHabitNameChanged = onHabitNameChanged,
        )
        Spacer(Modifier.size(12.dp))
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(56.dp)
                .background(AppColor.White, Shapes.medium)
        ) {
            Box(
                modifier = modifier
                    .align(Alignment.Center)
                    .size(50.dp)
                    .background(habitColor, Shapes.medium)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewHabitNameCard() {
    HabitNameCard(
        modifier = Modifier.background(AppColor.BgColorLightOrange),
        habitName = "Habit Name",
        habitColor = AppColor.habitIconColorYellow,
        onHabitNameChanged = {}

    )
}