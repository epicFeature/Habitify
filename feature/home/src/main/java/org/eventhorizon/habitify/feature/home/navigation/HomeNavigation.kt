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
// Может понадобиться для внутренних переходов или передачи дальше
    // Можно также передавать конкретные лямбды для навигации на другие фичи,
    // чтобы :feature:home не знал о других фичах напрямую.
    // onNavigateToNewHabit: () -> Unit,
    // onNavigateToHabitInfo: (String) -> Unit
) {
    composable(
        route = AppScreens.Home.ROUTE_DEFINITION, // Используем ROUTE_DEFINITION
        arguments = listOf(
            navArgument(
                AppScreens.Home.ARG_SHOW_CONGRATS_DIALOG
            ) {
                type = NavType.BoolType
                defaultValue = false
            }
        )
    ) { backStackEntry ->
        // 2. Получаем значение аргумента
        val showCongrats = backStackEntry.arguments?.getBoolean(AppScreens.Home.ARG_SHOW_CONGRATS_DIALOG) ?: false

        // 3. Вызываем HomeScreen, передавая флаг
        HomeScreen(
            showCongratsDialogOnStart = showCongrats, // Передаем флаг
            // Передаем действия навигации, которые будут вызывать методы NavController
            // из :app модуля или делегировать их дальше
            onOpenNewHabitScreenClick = onOpenNewHabitScreenClick, // Пример
            onOpenHabitInfoClick = onOpenHabitInfoClick/*{ habitId ->
                navController.navigate(AppScreens.HabitInfo.destination(habitId))
            }*/
        )
    }
}