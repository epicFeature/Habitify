package org.eventhorizon.habitify.ui.screens.habitinfo.components.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.ui.theme.AppColor
import org.eventhorizon.habitify.ui.theme.Shapes
import org.eventhorizon.habitify.ui.theme.calendarDateTextStyle
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarDay(
    modifier: Modifier = Modifier,
    date: LocalDate,
    color: Color,
    isCurrentMonth: Boolean,
    isChecked: Boolean
) {
    Column(
        modifier = modifier
            .background(if (isCurrentMonth) AppColor.BgColorLightOrange else AppColor.BgColorLightOrange.copy(0.5F), Shapes.medium)
            .wrapContentHeight()
            .width(46.dp)
            .padding(top = 6.dp, bottom = 4.dp, start = 4.dp, end = 4.dp),

    ) {
        // Число
        Text(
            text = date.dayOfMonth.toString(),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            style = calendarDateTextStyle,
            color = if (isCurrentMonth) AppColor.DarkPurple else AppColor.DarkPurple.copy(0.3F)
        )
        Spacer(Modifier.size(6.dp))
        // Квадрат для трекера
        HabitCheckCalendarCard(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = color,
            isChecked = isChecked
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun PreviewCalendarDayUnchecked() {
    CalendarDay(
        modifier = Modifier.background(AppColor.BgColorLightOrange),
        date = LocalDate.now(),
        color = AppColor.habitIconColorPurple,
        isCurrentMonth = true,
        isChecked = false
    )
}

@RequiresApi(Build.VERSION_CODES.O)
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


/*
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarDayTest(
    modifier: Modifier = Modifier,
    date: LocalDate,
    isCurrentMonth: Boolean,
    isChecked: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .aspectRatio(1f)
            .padding(4.dp)
    ) {
        // Число
        Text(
            text = date.dayOfMonth.toString(),
            color = if (isCurrentMonth) Color.Black else Color.Gray,
            fontSize = 14.sp
        )

        // Квадрат для трекера
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .aspectRatio(1f)
                .fillMaxWidth(0.6f)
                .background(
                    color = when {
                        !isCurrentMonth -> Color.LightGray
                        isChecked -> Color.Green
                        else -> Color.Red.copy(alpha = 0.3f)
                    }
                )
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun PreviewCalendarDayCheckedTest() {
    CalendarDayTest(
        modifier = Modifier.background(AppColor.BgColorLightOrange),
        date = LocalDate.now(),
        isCurrentMonth = true,
        isChecked = true
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun PreviewCalendarDayUncheckedTest() {
    CalendarDayTest(
        modifier = Modifier.background(AppColor.BgColorLightOrange),
        date = LocalDate.now(),
        isCurrentMonth = true,
        isChecked = false
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun PreviewCalendarDayCheckedNextMonthTest() {
    CalendarDayTest(
        modifier = Modifier.background(AppColor.BgColorLightOrange),
        date = LocalDate.now(),
        isCurrentMonth = false,
        isChecked = false
    )
}*/
