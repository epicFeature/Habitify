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
    /**
     * Экран Onboarding.
     */
    object Onboarding {
        const val ROUTE = "onboarding"
        fun destination(): String = ROUTE
    }
    /**
     * Константы и функции для навигации на главный экран.
     * Включает опциональный аргумент для показа диалога поздравления.
     */
    object Home {
        private const val ROUTE_PREFIX = "home"
        const val ARG_SHOW_CONGRATS_DIALOG = "showCongratsDialog"

        // Полный путь для NavGraphBuilder, включая плейсхолдер для опционального аргумента
        const val ROUTE_DEFINITION = "$ROUTE_PREFIX?$ARG_SHOW_CONGRATS_DIALOG={$ARG_SHOW_CONGRATS_DIALOG}"

        // Функция для построения пути при навигации (без аргумента)
        fun destination(): String = ROUTE_PREFIX

        // Функция для построения пути при навигации (с аргументом)
        fun destinationWithCongrats(show: Boolean): String = "$ROUTE_PREFIX?$ARG_SHOW_CONGRATS_DIALOG=$show"
    }

    /**
     * Экран создания новой привычки.
     */
    object NewHabit {
        const val ROUTE = "new_habit"
        fun destination(): String = ROUTE
    }

    object HabitInfo {
        private const val ROUTE_PREFIX = "habit_info"
        const val ARG_HABIT_ID = "habitId" // Ключ аргумента
        // Полный путь для NavGraphBuilder, включая плейсхолдер для аргумента
        const val ROUTE_DEFINITION = "$ROUTE_PREFIX/{$ARG_HABIT_ID}"
        // Функция для построения пути при навигации
        fun destination(habitId: String): String = "$ROUTE_PREFIX/$habitId"
    }
}


