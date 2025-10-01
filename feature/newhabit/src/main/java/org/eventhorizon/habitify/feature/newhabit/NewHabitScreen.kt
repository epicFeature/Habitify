package org.eventhorizon.habitify.feature.newhabit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.eventhorizon.habitify.feature.newhabit.components.appearance.HabitAppearanceCard
import org.eventhorizon.habitify.feature.newhabit.components.btn.DeleteBtn
import org.eventhorizon.habitify.feature.newhabit.components.btn.DoneBtn
import org.eventhorizon.habitify.feature.newhabit.components.duration.HabitDurationPlan
import org.eventhorizon.habitify.feature.newhabit.components.topcard.HabitNameCard
import org.eventhorizon.habitify.ui.components.theme.AppColor

@Composable
fun NewHabitScreen(modifier: Modifier,onGoToHomeScreenClick: () -> Unit) {
    Column(modifier = modifier.padding(20.dp,)) {
        HabitNameCard(
            modifier = Modifier
        )
        Spacer(Modifier.size(8.dp))
        HabitAppearanceCard(modifier = Modifier)
        Spacer(Modifier.size(8.dp))
        HabitDurationPlan(Modifier)
        Spacer(Modifier.weight(1F))
        Row (modifier=Modifier.fillMaxWidth()){
            val onDeleteBtnClick = null
            DeleteBtn(onClick = onGoToHomeScreenClick)
            Spacer(Modifier.weight(1F))
            DoneBtn (onClick = onGoToHomeScreenClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNewHabitScreen() {
    NewHabitScreen(
        modifier = Modifier
            .background(AppColor.BgColorLightOrange),
        onGoToHomeScreenClick = {}
    )
}