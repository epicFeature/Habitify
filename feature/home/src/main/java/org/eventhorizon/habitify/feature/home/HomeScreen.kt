package org.eventhorizon.habitify.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.eventhorizon.habitify.feature.home.components.PlusBtn
import org.eventhorizon.habitify.feature.home.components.QuoteCard
import org.eventhorizon.habitify.feature.home.components.habitschart.HabitHomeChart
import org.eventhorizon.habitify.ui.components.dialog.CongratulationsDialog
import org.eventhorizon.habitify.ui.components.theme.AppColor

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    showCongratsDialogOnStart: Boolean = false,
    onOpenNewHabitScreenClick: () -> Unit, //add new habit
    onOpenHabitInfoClick: (habitId: String) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    // Подписываемся на состояние и эффекты
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    // 2. При первом запуске экрана сообщаем ViewModel, нужно ли показать диалог
    LaunchedEffect(Unit) {
        if (showCongratsDialogOnStart) {
            homeViewModel.setEvent(HomeContract.HomeUiEvent.OnShowCongratsDialog)
        }
    }

    LaunchedEffect(Unit) {
        homeViewModel.effect.collect { effect ->
            when (effect) {
                is HomeContract.HomeUiEffect.NavigateToHabitInfo -> onOpenHabitInfoClick(effect.habitId)
                HomeContract.HomeUiEffect.NavigateToNewHabit -> onOpenNewHabitScreenClick()
            }
        }
    }

    if (uiState.showCongratulationsDialog) {
        CongratulationsDialog(
            onContinue = { homeViewModel.setEvent(HomeContract.HomeUiEvent.OnCongratulationsDialogContinue) },
            onCreateNewHabit = { homeViewModel.setEvent(HomeContract.HomeUiEvent.OnCongratulationsDialogCreateNew) }
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppColor.BgColorLightOrange)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            painter = painterResource(
                R.drawable.bg_home_screen
            ),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            QuoteCard(
                modifier = Modifier.padding(20.dp),
                quoteText = uiState.quoteText,
                quoteAuthor = uiState.quoteAuthor,
                isLoading = uiState.isLoadingQuote
            )
            Spacer(
                Modifier
                    .size(18.dp)
            )
            HabitHomeChart(
                modifier = Modifier,
                habits = uiState.habits,
                chartDays = uiState.chartDays,
                onHabitCheckClick = { habitId, date, isChecked ->
                    homeViewModel.setEvent(
                        HomeContract.HomeUiEvent.OnHabitCheckClick(
                            habitId, date, isChecked
                        )
                    )
                },
                onHabitCardClick = { habitId ->
                    homeViewModel.setEvent(
                        HomeContract.HomeUiEvent.OnHabitCardClick(habitId)
                    )
                }
            ) //todo пока так с // отступом, дальше решить как лучше
            Spacer(
                Modifier
                    .weight(1F)
                    .defaultMinSize(100.dp)
            )
        }
        PlusBtn(
            Modifier
                .padding(18.dp)
                .size(72.dp)
                .align(Alignment.BottomCenter),
            onClick = {  homeViewModel.setEvent(
                HomeContract.HomeUiEvent.OnAddHabitClick
            ) }
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(
        modifier = Modifier.background(AppColor.BgColorLightOrange),
        onOpenNewHabitScreenClick = {},
        onOpenHabitInfoClick = {},
    )
}