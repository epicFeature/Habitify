package org.eventhorizon.habitify.ui.navigation

import org.eventhorizon.habitify.R

enum class Routes(
    val title: Int? = null,
    val hasBackButton: Boolean = false
) {
    ONBOARDING,
    HOME(title = R.string.home_screen_title),
    HABIT_INFO(title = R.string.habit_info_screen_title_default, hasBackButton = true),
    NEW_HABIT(title = R.string.new_habit_screen_title, hasBackButton = true)
}