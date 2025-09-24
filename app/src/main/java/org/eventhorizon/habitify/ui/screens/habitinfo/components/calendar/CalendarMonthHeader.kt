package org.eventhorizon.habitify.ui.screens.habitinfo.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import org.eventhorizon.habitify.R
import org.eventhorizon.habitify.ui.theme.AppColor
import org.eventhorizon.habitify.ui.theme.calendarMonthTextStyle

@Composable
fun CalendarMonthHeader(modifier: Modifier = Modifier, monthTitle: String, onPreviousMonthClick: ()->Unit, onNextMonthClick: ()->Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ) {
        IconButton(onClick =onPreviousMonthClick) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = "Previous Month"
            )
        }

        Text(
            text = monthTitle,
            style = calendarMonthTextStyle,
        )

        IconButton(onClick = onNextMonthClick) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = "Next Month"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCalendarMonthHeader() {
    CalendarMonthHeader(
        modifier = Modifier.background(AppColor.BgColorLightOrange),
        monthTitle = "September",
        onPreviousMonthClick = {},
        onNextMonthClick = {}
    )
}