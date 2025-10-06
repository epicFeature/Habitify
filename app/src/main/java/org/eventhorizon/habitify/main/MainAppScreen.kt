package org.eventhorizon.habitify.main

import android.ruvpn.ui.common.components.CustomTopAppBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.eventhorizon.habitify.feature.habitinfo.navigation.habitInfoScreen
import org.eventhorizon.habitify.feature.home.navigation.homeScreen
import org.eventhorizon.habitify.feature.newhabit.navigation.newHabitScreen
import org.eventhorizon.habitify.feature.onboarding.navigation.onboardingScreen
import org.eventhorizon.habitify.navigation.AppScreens
import org.eventhorizon.habitify.ui.components.theme.AppColor


@Composable
fun MainAppScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val startDestinationState by mainViewModel.startDestination.collectAsState()

    when (val destination = startDestinationState) { //определение стартовой точки
        is StartDestination.Loading -> {
            //todo в будущем реализовать плавный SplashScreen
        }

        is StartDestination.Onboarding, is StartDestination.Main -> {
            val startRoute = if (destination is StartDestination.Onboarding) {
                AppScreens.Onboarding.ROUTE
            } else {
                AppScreens.Home.ROUTE_DEFINITION
            }
            HabitifyApp(
                startDestinationRoute = startRoute,
                onOnboardingFinished = mainViewModel::onOnboardingFinished,
                modifier = modifier
            )
        }
    }
}

@Composable
fun HabitifyApp(
    startDestinationRoute: String,
    onOnboardingFinished: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute != AppScreens.Onboarding.ROUTE) {
                val title = when (currentRoute) {
                    AppScreens.Home.ROUTE_DEFINITION -> "Home"
                    AppScreens.NewHabit.ROUTE -> "New Habit"
                    AppScreens.HabitInfo.ROUTE_DEFINITION -> "Habit info"
                    else -> "Habitify"
                }
                CustomTopAppBar(
                    title = title,
                    onBackClick = { navController.navigateUp() },
                )
            }
        },
        containerColor = if (currentRoute != AppScreens.Onboarding.ROUTE) {
            AppColor.BgColorLightOrange
        } else {
            AppColor.White
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestinationRoute,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            onboardingScreen(
                onOnboardingFinished = onOnboardingFinished
            )
            habitInfoScreen(
                Modifier,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateBackWithCompletion = {
                    navController.popBackStack()
                    navController.navigate(AppScreens.Home.destinationWithCongrats(true)) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
            newHabitScreen(
                onNavigateHome = {
                    navController.navigate(AppScreens.Home.destination()) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
            homeScreen(
                onOpenHabitInfoClick = { habitId ->
                    navController.navigate(AppScreens.HabitInfo.destination(habitId))
                },
                onOpenNewHabitScreenClick = { navController.navigate(AppScreens.NewHabit.ROUTE) }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainAppScreen(
        modifier = Modifier,
        mainViewModel = viewModel()
    )
}
