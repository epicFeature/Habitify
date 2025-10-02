package org.eventhorizon.habitify.feature.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.eventhorizon.habitify.feature.onboarding.OnboardingScreen
import org.eventhorizon.habitify.navigation.AppScreens


fun NavGraphBuilder.onboardingScreen(navController: NavHostController, onOnboardingFinished: () -> Unit) {
    composable(route = AppScreens.ONBOARDING) {
        OnboardingScreen(
            onOnboardingFinished =onOnboardingFinished
        )
    }
}