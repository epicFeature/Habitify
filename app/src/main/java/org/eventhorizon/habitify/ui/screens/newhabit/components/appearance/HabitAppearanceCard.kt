package org.eventhorizon.habitify.ui.screens.newhabit.components.appearance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.theme.AppColor
import org.eventhorizon.habitify.ui.theme.Shapes
import org.eventhorizon.habitify.ui.theme.homeChartHabitTitleTextStyle

@Composable
fun HabitAppearanceCard(modifier: Modifier, habitName: String="Sleep early") {
    Column(
        modifier = modifier
            .background(AppColor.White, Shapes.medium)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = habitName.uppercase(),
            modifier = Modifier
                .padding(18.dp),
            textAlign = TextAlign.Center,
            style = homeChartHabitTitleTextStyle
        )
        HorizontalDivider(
            color = AppColor.BgColorLightOrange,
            thickness = 1.dp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        HabitAppearDayCardLine(modifier= Modifier.align(Alignment.CenterHorizontally))
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHabitAppearanceCard() {
    HabitAppearanceCard(
        modifier = Modifier,
        habitName = "Sleep early"
    )
}