package org.eventhorizon.habitify.navigation

/*
sealed class AppScreens(routes: Routes) {
    data class OnboardingScreen(
        val routes: Routes = Routes.ONBOARDING
    ) : AppScreens(routes)
    data class HomeScreen(
        val routes: Routes = Routes.HOME
    ) : AppScreens(routes)
    data class HabitInfoScreen(
        val routes: Routes = Routes.HABIT_INFO,
        //val habitId: Int //todo просмотреть как тут можно передавать аргументы
    ) : AppScreens(routes)
    data class NewHabitScreen(
        val routes: Routes = Routes.NEW_HABIT
    ) : AppScreens(routes)
}*/
object AppScreens {
    const val ONBOARDING = "onboarding"
    const val HOME = "home"
    const val NEW_HABIT = "new_habit"

    object HabitInfo {
        private const val ROUTE_PREFIX = "habit_info"
        const val ARG_HABIT_ID = "habitId" // Ключ аргумента
        // Полный путь для NavGraphBuilder, включая плейсхолдер для аргумента
        const val ROUTE_DEFINITION = "$ROUTE_PREFIX/{$ARG_HABIT_ID}"
        // Функция для построения пути при навигации
        fun destination(habitId: String): String = "$ROUTE_PREFIX/$habitId"
    }
}


