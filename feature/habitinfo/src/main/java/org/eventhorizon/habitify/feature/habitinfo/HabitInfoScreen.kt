package org.eventhorizon.habitify.feature.habitinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.eventhorizon.habitify.feature.habitinfo.components.HabitInfoTopCard
import org.eventhorizon.habitify.feature.habitinfo.components.calendar.Calendar
import org.eventhorizon.habitify.ui.components.theme.AppColor
import org.eventhorizon.habitify.ui.components.theme.AppColor.DarkPurple
import org.eventhorizon.habitify.ui.components.theme.AppColor.OnbBtnDisablePurpleText
import org.eventhorizon.habitify.ui.components.theme.AppColor.OnbBtnOrange
import org.eventhorizon.habitify.ui.components.theme.Shapes
import org.eventhorizon.habitify.ui.components.theme.congratsDialogBtnTextStyle

@Composable
fun HabitInfoScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    onNavigateBackWithCompletion: () -> Unit,
    viewModel: HabitInfoViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                HabitInfoContract.HabitInfoEffect.NavigateBackToHome -> onNavigateBack()
                is HabitInfoContract.HabitInfoEffect.NavigateBackWithCompletion -> onNavigateBackWithCompletion()
            }
        }
    }

    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
            .background(AppColor.BgColorLightOrange)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.size(16.dp))
        HabitInfoTopCard(
            habitName = state.habitName,
            daysLeft = state.daysLeft,
            habitColor = state.habitColor
        )
        Spacer(Modifier.size(16.dp))
        Calendar(
            markedDates = state.markedDates,
            habitColor = state.habitColor
        )
        Spacer(Modifier.size(36.dp))
        //HabitInfoStatistics() //todo добавить позже при интеграции статистики
        //Spacer(Modifier.size(16.dp))
        Button(
            onClick = { viewModel.setEvent(HabitInfoContract.HabitInfoEvent.OnCompleteClick) },
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
                text = "Завершить привычку",
                style = congratsDialogBtnTextStyle
            )
        }
        Spacer(Modifier.height(10.dp))
        Button(
            onClick = { viewModel.setEvent(HabitInfoContract.HabitInfoEvent.OnDeleteClick) },
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
                text = "Удалить привычку",
                style = congratsDialogBtnTextStyle
            )
        }
        Spacer(Modifier.height(40.dp))
    }
}

@Preview(showBackground = true, heightDp = 2000)
@Composable
private fun PreviewHabitInfoScreen() {
    HabitInfoScreen(
        modifier = Modifier
            .background(AppColor.BgColorLightOrange),
        onNavigateBack = {},
        onNavigateBackWithCompletion = {},
        viewModel = viewModel(),
    )
}