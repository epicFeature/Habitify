package org.eventhorizon.habitify.ui.screens.home.components.habitschart.title

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DateHomeLineCard(modifier: Modifier = Modifier) { //todo подумать нужно ли это динамически или тут сама проставлю
    Row(modifier) {
        Spacer(Modifier.size(14.dp))
        DateHomeCard(dayOfWeekText = "Fri", dateText = "15")
        Spacer(Modifier.size(6.dp))
        DateHomeCard(dayOfWeekText = "Fri", dateText = "16")
        Spacer(Modifier.size(6.dp))
        DateHomeCard(dayOfWeekText = "Wed", dateText = "17")
        Spacer(Modifier.size(6.dp))
        DateHomeCard(dayOfWeekText = "Thu", dateText = "18")
        Spacer(Modifier.size(6.dp))
        DateHomeCard(dayOfWeekText = "Fri", dateText = "19", isToday = true) //это сегодня
    }
}

@Preview(showBackground = true, widthDp = 500)
@Composable
private fun PreviewDateHomeLineCard() {
    DateHomeLineCard()
}