package org.eventhorizon.habitify.feature.habitinfo.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.eventhorizon.habitify.feature.habitinfo.HabitInfoScreen
import org.eventhorizon.habitify.navigation.AppScreens

// feature/habitinfo/src/main/java/org/eventhorizon/habitify/feature/habitinfo/navigation/HabitInfoNavigation.kt
@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.habitInfoScreen(navController: NavHostController) {
    composable(
        route = AppScreens.HabitInfo.ROUTE_DEFINITION,
        arguments = listOf(navArgument(AppScreens.HabitInfo.ARG_HABIT_ID) {
            type = NavType.StringType
        })
    ) { backStackEntry ->
        val habitId = backStackEntry.arguments?.getString(AppScreens.HabitInfo.ARG_HABIT_ID)
        // Важно обработать случай, когда habitId может быть null, если это возможно
        if (habitId != null) {
            HabitInfoScreen(
                habitId = habitId
            )
        } else {
            // Обработка ошибки: неизвестный ID или неправильный маршрут
            // Можно показать экран ошибки или вернуться назад
            navController.popBackStack()
        }
    }
}