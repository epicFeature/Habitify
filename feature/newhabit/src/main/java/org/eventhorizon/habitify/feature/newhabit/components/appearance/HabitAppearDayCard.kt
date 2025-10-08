package org.eventhorizon.habitify.feature.newhabit.components.appearance


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import org.eventhorizon.habitify.ui.components.theme.homeDateCardDayOfWeekTextStyle

@Composable
fun HabitAppearDayCard(
    modifier: Modifier,
    isChecked: Boolean,
    dayOfWeekText: String,
    color: Color
) {
    Column(modifier = modifier.background(AppColor.White)) {
        Text(
            text = dayOfWeekText.uppercase(),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp),
            textAlign = TextAlign.Center,
            style = homeDateCardDayOfWeekTextStyle
        )
        HabitCheckCalendarCard(
            modifier = Modifier
                .padding(start = 4.dp, top = 8.dp, end = 4.dp, bottom = 16.dp),
            color = color,
            isChecked = isChecked
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHabitAppearDayCard() {
    HabitAppearDayCard(
        modifier = Modifier,
        isChecked = true,
        dayOfWeekText = "mon",
        color = AppColor.habitIconColorYellow
    )
}
