package org.eventhorizon.habitify.feature.home.components.habitschart.title

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.feature.home.R
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.Shapes
import org.eventhorizon.habitify.ui.components.theme.homeDateCardDateTextStyle
import org.eventhorizon.habitify.ui.components.theme.homeDateCardDayOfWeekTextStyle

@Composable
fun DateHomeCard(
    modifier: Modifier = Modifier,
    dayOfWeekText: String,
    dateText: String,
    isToday: Boolean = false
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .background(AppColor.White, Shapes.medium)
                .size(50.dp)
                .wrapContentSize()
        ) {
            Text(
                text = dayOfWeekText.uppercase(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                style = homeDateCardDayOfWeekTextStyle
            )
            Text(
                    text = dateText,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    style = homeDateCardDateTextStyle
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
private fun PreviewDateHomeCard() {
    DateHomeCard(
        modifier = Modifier,
        dayOfWeekText = "Mon",
        dateText = "15",
        isToday = true
    )
}