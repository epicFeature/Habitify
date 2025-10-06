package org.eventhorizon.habitify.feature.habitinfo.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.components.calendar.HabitCheckCalendarCard
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.Shapes
import org.eventhorizon.habitify.ui.components.theme.habitInfoTopCardSubtitleTextStyle
import org.eventhorizon.habitify.ui.components.theme.habitInfoTopCardTitleTextStyle

@Composable
fun HabitInfoTopCard(
    modifier: Modifier = Modifier,
    habitName: String,
    daysLeft: Int,
    habitColor: Color
) {
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
            color = habitColor,
            isChecked = true
        )
        Spacer(Modifier.size(16.dp))
        Column(
            modifier = Modifier
                .wrapContentSize()
        ) {
            Text(
                text = habitName,
                modifier = Modifier,
                textAlign = TextAlign.Start,
                style = habitInfoTopCardTitleTextStyle
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Осталось ещё $daysLeft дней",
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
        modifier = Modifier.background(AppColor.BgColorLightOrange),
        habitName = "Sleep early",
        daysLeft = 6,
        habitColor = AppColor.habitIconColorYellow
    )
}