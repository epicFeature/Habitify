package org.eventhorizon.habitify.main

import android.os.Build
import android.ruvpn.ui.common.components.CustomTopAppBar
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
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


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainAppScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    val startDestinationState by mainViewModel.startDestination.collectAsState()

    when (val destination = startDestinationState) {
        is StartDestination.Loading -> {
            //SplashScreen(modifier = modifier)
        }

        is StartDestination.Onboarding, is StartDestination.Main -> {
            // Определяем строковый маршрут для NavHost
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


@Preview
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppColor.White),
        contentAlignment = Alignment.Center
    ) {}
}


/**
 * Основной Composable, содержащий Scaffold и NavHost.
 * Он не зависит от ViewModel и получает все необходимое через параметры.
 */
@RequiresApi(Build.VERSION_CODES.O)
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
                    // Вызываем безопасную функцию-конструктор
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

/*
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainAppScreen(
        modifier = Modifier
    )
}*/
