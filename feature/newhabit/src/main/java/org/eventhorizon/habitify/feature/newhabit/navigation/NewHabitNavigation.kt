package org.eventhorizon.habitify.feature.newhabit.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.eventhorizon.habitify.feature.newhabit.NewHabitScreen
import org.eventhorizon.habitify.navigation.AppScreens

fun NavGraphBuilder.newHabitScreen(navController: NavHostController) {
    composable(route = AppScreens.NEW_HABIT) {
        NewHabitScreen(
            modifier = Modifier,
            onGoToHomeScreenClick = {
                navController.navigate(AppScreens.HOME)
            }
        )
    }
}