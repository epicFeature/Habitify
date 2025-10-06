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
    color: Color = AppColor.habitIconColorYellow) {
    Row(modifier = modifier //todo в очереди на оптимизацию
        .background(AppColor.BgColorLightOrange),
        horizontalArrangement = Arrangement.Center
    ) {
        HabitAppearDayCard(modifier = Modifier.background(AppColor.White), isChecked = appearanceDays[0], "MON", color = color)
        Spacer(Modifier.size(1.dp))
        HabitAppearDayCard(modifier = Modifier.background(AppColor.White), isChecked = appearanceDays[1], "TUE", color = color)
        Spacer(Modifier.size(1.dp))
        HabitAppearDayCard(modifier = Modifier.background(AppColor.White), isChecked = appearanceDays[2], "WED", color = color)
        Spacer(Modifier.size(1.dp))
        HabitAppearDayCard(modifier = Modifier.background(AppColor.White), isChecked = appearanceDays[3], "THU", color = color)
        Spacer(Modifier.size(1.dp))
        HabitAppearDayCard(modifier = Modifier.background(AppColor.White), isChecked = appearanceDays[4], "FRI", color = color)
        Spacer(Modifier.size(1.dp))
        HabitAppearDayCard(modifier = Modifier.background(AppColor.White), isChecked = appearanceDays[5], "SUT", color = color)
        Spacer(Modifier.size(1.dp))
        HabitAppearDayCard(modifier = Modifier.background(AppColor.White), isChecked = appearanceDays[6], "SUN", color = color)
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