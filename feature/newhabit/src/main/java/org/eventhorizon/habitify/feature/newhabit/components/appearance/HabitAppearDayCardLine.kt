package org.eventhorizon.habitify.feature.newhabit.components.appearance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
fun HabitAppearDayCardLine(
    modifier: Modifier,
    appearanceDays: List<Boolean>,
    color: Color = AppColor.habitIconColorYellow
) {
    val days = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")
    Row(
        modifier = modifier
            .background(AppColor.BgColorLightOrange),
        horizontalArrangement = Arrangement.Center
    ) {
        days.forEachIndexed { index, day ->
            HabitAppearDayCard(
                modifier = Modifier.background(AppColor.White),
                isChecked = appearanceDays[0],
                day,
                color = color
            )
            if (index != days.lastIndex) Spacer(Modifier.size(1.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHabitAppearDayCardLine() {
    HabitAppearDayCardLine(
        modifier = Modifier,
        appearanceDays = listOf(true, true, true, true, false, true, false)
    )
}