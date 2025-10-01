package org.eventhorizon.habitify.feature.home.components.habitschart.body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.Shapes
import org.eventhorizon.habitify.ui.components.theme.homeChartHabitTitleTextStyle

@Composable
fun HabitHomeCard(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    habitName: String, /*habitTrackingDataHome: HabitTrackingDataHome*/
) { //todo пока хардкод потом заменить
    Row(
        modifier = modifier
            .background(AppColor.White, Shapes.medium)
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = habitName.uppercase(),
            modifier = Modifier
                .padding(18.dp)
                .width(84.dp)
                .align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            style = homeChartHabitTitleTextStyle
        )
        VerticalDivider(
            color = AppColor.BgColorLightOrange,
            thickness = 1.dp,
            modifier = Modifier.height(74.dp)
        )
        HabitHomeLineCard(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.CenterVertically),
            color = AppColor.habitIconColorPurple
        )
    }
}

@Preview(showBackground = true, widthDp = 500)
@Composable
private fun PreviewHabitHomeCard() {
    HabitHomeCard(onClick = {}, habitName = "Early Wake Ups")
}

