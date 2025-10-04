package org.eventhorizon.habitify.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.eventhorizon.habitify.feature.home.HomeScreen
import org.eventhorizon.habitify.navigation.AppScreens


fun NavGraphBuilder.homeScreen(
    navController: NavHostController // Может понадобиться для внутренних переходов или передачи дальше
    // Можно также передавать конкретные лямбды для навигации на другие фичи,
    // чтобы :feature:home не знал о других фичах напрямую.
    // onNavigateToNewHabit: () -> Unit,
    // onNavigateToHabitInfo: (String) -> Unit
) {
    composable(route = AppScreens.HOME) {
        HomeScreen(
            // Передаем действия навигации, которые будут вызывать методы NavController
            // из :app модуля или делегировать их дальше
            onOpenNewHabitScreenClick = { navController.navigate(AppScreens.NEW_HABIT) }, // Пример
            onOpenHabitInfoClick = { habitId ->
                navController.navigate(AppScreens.HabitInfo.destination(habitId))
            }
        )
    }
}