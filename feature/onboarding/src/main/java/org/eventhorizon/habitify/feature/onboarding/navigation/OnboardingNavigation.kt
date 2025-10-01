package org.eventhorizon.habitify.feature.onboarding.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.eventhorizon.habitify.feature.onboarding.OnboardingScreen
import org.eventhorizon.habitify.navigation.AppScreens


fun NavGraphBuilder.onboardingScreen(navController: NavHostController) {
    composable(route = AppScreens.ONBOARDING) {
        OnboardingScreen(
            onSkipClick = {
                navController.navigate(AppScreens.HOME)
            },
            modifier = Modifier //возможно Modifier стоит тут выводить
        )
    }
}