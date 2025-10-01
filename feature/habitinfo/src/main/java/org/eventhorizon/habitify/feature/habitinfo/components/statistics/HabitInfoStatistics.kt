package org.eventhorizon.habitify.feature.habitinfo.components.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.Shapes

@Composable
fun HabitInfoStatistics(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .background(AppColor.White, Shapes.medium)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            StatisticsItemCard(Modifier.weight(1F))
            VerticalDivider(
                color = AppColor.BgColorLightOrange,
                thickness = 1.dp,
                modifier = Modifier.height(90.dp).align(Alignment.Bottom)
            )
            StatisticsItemCard(Modifier.weight(1F))
        }
        HorizontalDivider(
            color = AppColor.BgColorLightOrange,
            thickness = 1.dp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        StatisticsItemCard(Modifier.fillMaxWidth())
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewHabitInfoStatistics() {
    HabitInfoStatistics(
        modifier = Modifier.background(AppColor.BgColorLightOrange)
    )
}