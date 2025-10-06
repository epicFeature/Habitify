package org.eventhorizon.habitify.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.eventhorizon.habitify.feature.home.HomeScreen
import org.eventhorizon.habitify.navigation.AppScreens


fun NavGraphBuilder.homeScreen(
    onOpenNewHabitScreenClick: () -> Unit,
    onOpenHabitInfoClick: (habitId: String) -> Unit
) {
    composable(
        route = AppScreens.Home.ROUTE_DEFINITION,
        arguments = listOf(
            navArgument(
                AppScreens.Home.ARG_SHOW_CONGRATS_DIALOG
            ) {
                type = NavType.BoolType
                defaultValue = false
            }
        )
    ) { backStackEntry ->
        val showCongrats =
            backStackEntry.arguments?.getBoolean(AppScreens.Home.ARG_SHOW_CONGRATS_DIALOG)
                ?: false //получаем значение из аргументов

        HomeScreen(
            showCongratsDialogOnStart = showCongrats,
            onOpenNewHabitScreenClick = onOpenNewHabitScreenClick,
            onOpenHabitInfoClick = onOpenHabitInfoClick
        )
    }
}