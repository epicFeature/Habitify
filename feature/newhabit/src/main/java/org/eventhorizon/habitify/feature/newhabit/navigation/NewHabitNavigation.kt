package org.eventhorizon.habitify.feature.newhabit.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.eventhorizon.habitify.feature.newhabit.NewHabitScreen
import org.eventhorizon.habitify.navigation.AppScreens

fun NavGraphBuilder.newHabitScreen(
    onNavigateHome: () -> Unit
) {
    composable(route = AppScreens.NewHabit.ROUTE) {
        NewHabitScreen(
            modifier = Modifier,
            onNavigateHome = onNavigateHome
        )
    }
}