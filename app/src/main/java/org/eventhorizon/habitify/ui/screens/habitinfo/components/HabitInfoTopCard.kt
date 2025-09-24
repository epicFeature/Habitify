package org.eventhorizon.habitify.ui.screens.habitinfo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.screens.habitinfo.components.calendar.HabitCheckCalendarCard
import org.eventhorizon.habitify.ui.theme.AppColor
import org.eventhorizon.habitify.ui.theme.Shapes
import org.eventhorizon.habitify.ui.theme.habitInfoTopCardSubtitleTextStyle
import org.eventhorizon.habitify.ui.theme.habitInfoTopCardTitleTextStyle

@Composable
fun HabitInfoTopCard(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(AppColor.White, Shapes.medium)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp)
    ) {
        HabitCheckCalendarCard(
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterVertically),
            color = AppColor.habitIconColorYellow,
            isChecked = true
        )
        Spacer(Modifier.size(16.dp))
        Column(
            modifier = Modifier
                .wrapContentSize()
        ) {
            Text(
                text = "Sleep early",
                modifier = Modifier,
                textAlign = TextAlign.Start,
                style = habitInfoTopCardTitleTextStyle
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "You need 7 more days", //todo сделать динамичным
                modifier = Modifier,
                textAlign = TextAlign.Start,
                style = habitInfoTopCardSubtitleTextStyle
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHabitInfoTopCard() {
    HabitInfoTopCard(
        modifier = Modifier.background(AppColor.BgColorLightOrange)
    )
}