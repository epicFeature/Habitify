package org.eventhorizon.habitify.feature.habitinfo.components.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.feature.habitinfo.R
import org.eventhorizon.habitify.ui.components.calendar.HabitCheckCalendarCard
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.Shapes
import org.eventhorizon.habitify.ui.components.theme.calendarDateTextStyle
import java.time.LocalDate

@Composable
fun CalendarDay(
    modifier: Modifier = Modifier,
    date: LocalDate,
    color: Color,
    isToday: Boolean = false,
    isCurrentMonth: Boolean,
    isChecked: Boolean
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
                .padding(vertical = 1.dp, horizontal=2.dp)
                .background(
                    if (isCurrentMonth) AppColor.BgColorLightOrange else AppColor.BgColorLightOrange.copy(
                        0.5F
                    ), Shapes.medium
                )
                .wrapContentHeight()
                .width(46.dp)
                .padding(top = 6.dp, bottom = 4.dp, start = 4.dp, end = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally

            ) {
            Text(
                text = date.dayOfMonth.toString(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                style = calendarDateTextStyle,
                color = if (isCurrentMonth) AppColor.DarkPurple else AppColor.DarkPurple.copy(0.3F)
            )
            Spacer(Modifier.size(6.dp))
            HabitCheckCalendarCard(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = color,
                isChecked = isChecked
            )
        }
        if (isToday) {
            Image(
                painter = painterResource(id = R.drawable.ic_today_line),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 2.dp)
                    .width(36.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCalendarDayChecked() {
    CalendarDay(
        modifier = Modifier.background(AppColor.BgColorLightOrange),
        date = LocalDate.now(),
        color = AppColor.habitIconColorPurple,
        isCurrentMonth = true,
        isChecked = true
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewCalendarDayUnchecked() {
    CalendarDay(
        modifier = Modifier.background(AppColor.BgColorLightOrange),
        date = LocalDate.now(),
        color = AppColor.habitIconColorPurple,
        isToday = true,
        isCurrentMonth = true,
        isChecked = false
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewCalendarDayCheckedNextMonth() {
    CalendarDay(
        modifier = Modifier.background(AppColor.BgColorLightOrange),
        date = LocalDate.now(),
        color = AppColor.habitIconColorPurple,
        isCurrentMonth = false,
        isChecked = false
    )
}