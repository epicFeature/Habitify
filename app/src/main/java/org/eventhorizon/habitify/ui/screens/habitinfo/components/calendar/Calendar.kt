package org.eventhorizon.habitify.ui.screens.habitinfo.components.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.eventhorizon.habitify.ui.theme.AppColor
import org.eventhorizon.habitify.ui.theme.Shapes

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(modifier: Modifier = Modifier, viewModel: CalendarViewModel = viewModel()) {
    Column(modifier = modifier.fillMaxWidth().background(AppColor.OnbBgWhite, Shapes.medium).padding(horizontal = 2.dp)) {
        // Заголовок с навигацией
        CalendarMonthHeader(
            modifier = Modifier.fillMaxWidth(),
            monthTitle = viewModel.getMonthTitle(),
            onPreviousMonthClick = { viewModel.navigateToPreviousMonth() },
            onNextMonthClick = { viewModel.navigateToNextMonth() }
        )
        Spacer(Modifier.size(16.dp))
        // Дни недели
        CalendarDayOfWeekHeader(modifier = Modifier.padding(horizontal = 3.dp))
        Spacer(Modifier.size(8.dp))

        // Сетка дней
        CalendarDaysGrid(Modifier, viewModel)

        Spacer(Modifier.size(12.dp))
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun PreviewCalendar() {
    Calendar(
        modifier = Modifier.background(AppColor.BgColorLightOrange)
    )
}