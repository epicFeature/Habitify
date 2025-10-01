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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.feature.home.components.PlusBtn
import org.eventhorizon.habitify.feature.home.components.QuoteCard
import org.eventhorizon.habitify.feature.home.components.habitschart.HabitHomeChart
import org.eventhorizon.habitify.ui.components.theme.AppColor

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onOpenNewHabitScreenClick: () -> Unit, //add new habit
    onOpenHabitInfoClick: (habitId: String) -> Unit
    //homeViewModel: HomeViewModel = hiltViewModel()
) {
    //val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

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
        Column { //todo добавить вертикальную скролируемость
            QuoteCard(
                modifier = Modifier.padding(20.dp),
                quoteText = "We first make our habits, \n" +
                        "and then our habits \n" +
                        "makes us.", quoteAuthor = "anonymous"
            )
            Spacer(
                Modifier
                    .size(18.dp)
            )
            HabitHomeChart(onHabitClick = onOpenHabitInfoClick) //todo пока так с отступом, дальше решить как лучше
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
            onClick = onOpenNewHabitScreenClick
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