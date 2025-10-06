package org.eventhorizon.habitify.navigation

object AppScreens {
    object Onboarding {
        const val ROUTE = "onboarding"
        fun destination(): String = ROUTE
    }
    object Home {
        private const val ROUTE_PREFIX = "home"
        const val ARG_SHOW_CONGRATS_DIALOG = "showCongratsDialog"
        const val ROUTE_DEFINITION = "$ROUTE_PREFIX?$ARG_SHOW_CONGRATS_DIALOG={$ARG_SHOW_CONGRATS_DIALOG}"
        fun destination(): String = ROUTE_PREFIX //навигация без аргумента
        fun destinationWithCongrats(show: Boolean): String = "$ROUTE_PREFIX?$ARG_SHOW_CONGRATS_DIALOG=$show" //навигация с аргументом
    }
    object NewHabit {
        const val ROUTE = "new_habit"
        fun destination(): String = ROUTE
    }
    object HabitInfo {
        private const val ROUTE_PREFIX = "habit_info"
        const val ARG_HABIT_ID = "habitId"
        const val ROUTE_DEFINITION = "$ROUTE_PREFIX/{$ARG_HABIT_ID}"
        fun destination(habitId: String): String = "$ROUTE_PREFIX/$habitId"
    }
}


