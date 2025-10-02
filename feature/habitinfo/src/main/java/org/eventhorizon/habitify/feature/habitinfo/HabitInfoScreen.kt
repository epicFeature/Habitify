package org.eventhorizon.habitify.feature.habitinfo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.feature.habitinfo.components.HabitInfoTopCard
import org.eventhorizon.habitify.feature.habitinfo.components.calendar.Calendar
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.AppColor.DarkPurple
import org.eventhorizon.habitify.ui.components.theme.AppColor.OnbBtnDisablePurpleText
import org.eventhorizon.habitify.ui.components.theme.AppColor.OnbBtnOrange
import org.eventhorizon.habitify.ui.components.theme.Shapes
import org.eventhorizon.habitify.ui.components.theme.congratsDialogBtnTextStyle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HabitInfoScreen(modifier: Modifier = Modifier, habitId: String) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
            .background(AppColor.BgColorLightOrange)
    ) {
        Spacer(Modifier.size(16.dp))
        HabitInfoTopCard()
        Spacer(Modifier.size(16.dp))
        Calendar()
        Spacer(Modifier.size(36.dp))
        //HabitInfoStatistics()
        //Spacer(Modifier.size(16.dp))
        Button(
            onClick = { }, //todo onClick
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            contentPadding = PaddingValues(vertical = 22.dp),
            shape = Shapes.small,
            colors = ButtonColors(
                contentColor = DarkPurple,
                containerColor = OnbBtnOrange,
                disabledContentColor = OnbBtnDisablePurpleText,
                disabledContainerColor = OnbBtnOrange
            )
        ) {
            Text(
                text = "Complete",
                style = congratsDialogBtnTextStyle
            )
        }
        Spacer(Modifier.height(10.dp))
        Button(
            onClick = {}, //todo onCLick
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            contentPadding = PaddingValues(vertical = 22.dp),
            shape = Shapes.small,
            colors = ButtonColors(
                contentColor = DarkPurple,
                containerColor = AppColor.White,
                disabledContentColor = OnbBtnDisablePurpleText,
                disabledContainerColor = AppColor.White
            )
        ) {
            Text(
                text = "Delete",
                style = congratsDialogBtnTextStyle
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, heightDp = 2000)
@Composable
private fun PreviewHabitInfoScreen() {
    HabitInfoScreen(
        modifier = Modifier
            .background(AppColor.BgColorLightOrange),
        habitId = ""
    )
}