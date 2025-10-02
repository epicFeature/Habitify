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

    /*val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Routes.valueOf(
        backStackEntry?.destination?.route ?: Routes.ONBOARDING.name
    )*/
    // 1. Получаем состояние из ViewModel. Compose будет следить за его изменениями.
    val startDestinationState by mainViewModel.startDestination.collectAsState()

    // 2. В зависимости от состояния, решаем, что показать: Splash, Onboarding или Main.
    when (val destination = startDestinationState) {
        // Пока ViewModel определяет маршрут, показываем Splash-экран
        is StartDestination.Loading -> {
            //SplashScreen(modifier = modifier)
        }
        // Когда маршрут определен (Onboarding или Main), запускаем основной UI
        is StartDestination.Onboarding, is StartDestination.Main -> {
            // Определяем строковый маршрут для NavHost
            val startRoute = if (destination is StartDestination.Onboarding) {
                AppScreens.ONBOARDING
            } else {
                AppScreens.HOME
            }
            // Передаем маршрут и колбэк в основной Composable приложения
            HabitifyApp(
                startDestinationRoute = startRoute,
                onOnboardingFinished = mainViewModel::onOnboardingFinished,
                modifier = modifier
            )
        }
    }
}


/**
 * Простой Splash-экран, который показывает изображение.
 */
@Preview
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize().background(AppColor.White),
        contentAlignment = Alignment.Center
    ){}
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
            if (currentRoute != AppScreens.ONBOARDING) {
                val title = when (currentRoute) {
                    AppScreens.HOME -> "Home"
                    AppScreens.NEW_HABIT -> "New Habit"
                    AppScreens.HabitInfo.ROUTE_DEFINITION -> "Habit info"
                    else -> "Habitify"
                }
                CustomTopAppBar(
                    title = title,
                    onBackClick = { navController.navigateUp() },
                )
            }
        },
        containerColor = if (currentRoute != AppScreens.ONBOARDING) {
            AppColor.BgColorLightOrange
        } else {
            AppColor.White
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            // 3. Используем динамический стартовый маршрут
            startDestination = startDestinationRoute,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
            // Modifier.verticalScroll был удален, т.к. он мешает скроллингу внутри экранов
        ) {
            // 4. Передаем колбэк в экран онбординга
            onboardingScreen(
                navController = navController,
                onOnboardingFinished = onOnboardingFinished
            )
            habitInfoScreen(navController)
            newHabitScreen(navController)
            homeScreen(navController)
        }
    }
}

//            composable(route = Routes.ONBOARDING.name) { //todo тщательно продумать всю навигацию и переделать. Это копипаст почти
//                OnboardingScreen(
//                    modifier = Modifier,
//                    onSkipClick = {
//                        // viewModel.handleEvent(MainContract.MainUiEvent.OnOnboardingFinished)
//                        navController.navigate(Routes.HOME.name) {
//                            popUpTo(navController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                        }
//                    })
//            }
//            composable(route = Routes.HOME.name) {
//                HomeScreen(
//                    modifier = Modifier,
//                    onPlusClick = {
//                        navController.navigate(Routes.NEW_HABIT.name) {
//                            launchSingleTop = true
//                        }
//                    },
//                    onHabitCardClick = {
//                        navController.navigate(Routes.HABIT_INFO.name) {
//                            launchSingleTop = true
//                        }
//                    }
//                )
//            }
//            composable(route = Routes.HABIT_INFO.name) {
//                HabitInfoScreen(
//                    modifier = Modifier
//                )
//            }
//            composable(route = Routes.NEW_HABIT.name) {
//                NewHabitScreen(modifier = Modifier)
//            }

/*
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainAppScreen(
        modifier = Modifier
    )
}*/
