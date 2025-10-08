package org.eventhorizon.habitify.feature.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.eventhorizon.habitify.feature.onboarding.OnboardingScreen
import org.eventhorizon.habitify.navigation.AppScreens


fun NavGraphBuilder.onboardingScreen(onOnboardingFinished: () -> Unit) {
    composable(route = AppScreens.Onboarding.ROUTE) {
        OnboardingScreen(
            onOnboardingFinished =onOnboardingFinished
        )
    }
}