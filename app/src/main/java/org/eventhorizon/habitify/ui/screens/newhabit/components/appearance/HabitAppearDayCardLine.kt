package org.eventhorizon.habitify.ui.screens.newhabit.components.appearance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.theme.AppColor

@Composable
fun HabitAppearDayCardLine(modifier: Modifier) {
    Row(modifier = modifier
        .background(AppColor.BgColorLightOrange),
        horizontalArrangement = Arrangement.Center
    ) {
        HabitAppearDayCard(modifier = Modifier.background(AppColor.White), isChecked = true, "MON")
        Spacer(Modifier.size(1.dp))
        HabitAppearDayCard(modifier = Modifier.background(AppColor.White), isChecked = true, "TUE")
        Spacer(Modifier.size(1.dp))
        HabitAppearDayCard(modifier = Modifier.background(AppColor.White), isChecked = true, "WED")
        Spacer(Modifier.size(1.dp))
        HabitAppearDayCard(modifier = Modifier.background(AppColor.White), isChecked = true, "THU")
        Spacer(Modifier.size(1.dp))
        HabitAppearDayCard(modifier = Modifier.background(AppColor.White), isChecked = true, "FRI")
        Spacer(Modifier.size(1.dp))
        HabitAppearDayCard(modifier = Modifier.background(AppColor.White), isChecked = true, "SUT")
        Spacer(Modifier.size(1.dp))
        HabitAppearDayCard(modifier = Modifier.background(AppColor.White), isChecked = true, "SUN")
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHabitAppearDayCardLine() {
    HabitAppearDayCardLine(
        modifier = Modifier
    )
}