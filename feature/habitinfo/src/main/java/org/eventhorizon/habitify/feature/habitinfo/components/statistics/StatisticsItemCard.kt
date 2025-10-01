package org.eventhorizon.habitify.feature.habitinfo.components.statistics

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.feature.habitinfo.R
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.analyticsSubtitleTextStyle
import org.eventhorizon.habitify.ui.components.theme.analyticsTitleTextStyle

@Composable
fun StatisticsItemCard(modifier: Modifier= Modifier, statsTitle: String="Longest Streak", statsData: String="20 Days") {
    Row(
        modifier = modifier
            .padding(start = 18.dp, end=10.dp, top = 18.dp, bottom = 18.dp)
            .wrapContentSize()
    ) {
        Column(
            modifier = Modifier

                .wrapContentSize()
        ) {
            Text(
                text = statsData.uppercase(),
                modifier = Modifier,
                textAlign = TextAlign.Start,
                style = analyticsTitleTextStyle
            )
            Text(
                text = statsTitle,
                modifier = Modifier,
                textAlign = TextAlign.Start,
                style = analyticsSubtitleTextStyle
            )
        }
        Spacer(Modifier.size(4.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_fire),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(8.dp)
                .size(38.dp)

        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewStatisticsItemCard() {
    StatisticsItemCard(
        modifier = Modifier.background(AppColor.White),
        statsData = "20 Days",
        statsTitle = "Longest Streak"
    )
}