package org.eventhorizon.habitify.feature.habitinfo.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.eventhorizon.habitify.feature.habitinfo.HabitInfoScreen
import org.eventhorizon.habitify.navigation.AppScreens


fun NavGraphBuilder.habitInfoScreen(
    // 1. Принимаем лямбды для навигации
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
        // Мы уже находимся внутри composable, backStackEntry доступен здесь.
        // viewModel будет создан автоматически с нужным habitId.
        HabitInfoScreen(
            modifier = modifier,
            onNavigateBack = onNavigateBack,
            onNavigateBackWithCompletion = onNavigateBackWithCompletion
        )

        /*val habitId = backStackEntry.arguments?.getString(AppScreens.HabitInfo.ARG_HABIT_ID)
        // Важно обработать случай, когда habitId может быть null, если это возможно
        if (habitId != null) {
            HabitInfoScreen(
                habitId = habitId
            )
        } else {
            // Обработка ошибки: неизвестный ID или неправильный маршрут
            // Можно показать экран ошибки или вернуться назад
            navController.popBackStack()
        }*/
    }
}