package org.eventhorizon.habitify.feature.habitinfo.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.calendarDayOfWeekTextStyle

@Composable
fun CalendarDayOfWeekHeader(modifier: Modifier = Modifier) {
    val days = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
    Row(modifier = modifier.fillMaxWidth()) {
        days.forEachIndexed { index, day ->
            Text(
                text = day,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
                    .width(46.dp),
                textAlign = TextAlign.Center,
                style = calendarDayOfWeekTextStyle
            )
            if (index != days.lastIndex) Spacer(Modifier.size(6.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCalendarDayOfWeekHeader() {
    CalendarDayOfWeekHeader(
        modifier = Modifier.background(AppColor.BgColorLightOrange)
    )
}