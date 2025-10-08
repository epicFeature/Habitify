package org.eventhorizon.habitify.feature.habitinfo.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.eventhorizon.habitify.feature.habitinfo.HabitInfoScreen
import org.eventhorizon.habitify.navigation.AppScreens


fun NavGraphBuilder.habitInfoScreen(
    modifier: Modifier,
    onNavigateBack: () -> Unit,
    onNavigateBackWithCompletion: () -> Unit
) {
    composable(
        route = AppScreens.HabitInfo.ROUTE_DEFINITION,
        arguments = listOf(navArgument(AppScreens.HabitInfo.ARG_HABIT_ID) {
            type = NavType.StringType
        })
    ) { backStackEntry ->
        HabitInfoScreen(
            modifier = modifier,
            onNavigateBack = onNavigateBack,
            onNavigateBackWithCompletion = onNavigateBackWithCompletion
        )
    }
}