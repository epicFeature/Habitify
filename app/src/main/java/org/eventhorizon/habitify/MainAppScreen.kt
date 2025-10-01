package org.eventhorizon.habitify

import android.os.Build
import android.ruvpn.ui.common.components.CustomTopAppBar
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    modifier: Modifier
) {

    /*val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Routes.valueOf(
        backStackEntry?.destination?.route ?: Routes.ONBOARDING.name
    )*/

    val navController: NavHostController = rememberNavController()
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute != AppScreens.ONBOARDING /* && currentRoute != null */) { // Пример условия
                val title = when (currentRoute) {
                    AppScreens.HOME -> "Home"
                    AppScreens.NEW_HABIT -> "New Habit"
                    AppScreens.HabitInfo.ROUTE_DEFINITION -> {
                        // Для экранов с аргументами, заголовок может быть динамическим
                        // и его лучше получать из ViewModel соответствующего экрана.
                        // Пока что просто "Details"
                        "Details"
                    }

                    else -> "Habitify"
                }
                // Предположим, что CustomTopAppBar теперь принимает title и onBackClick
                // и, возможно, флаг hasBackButton
                CustomTopAppBar(
                    title = title,
                    // currentScreen = ... // Если CustomTopAppBar все еще зависит от вашего Routes/AppScreens enum
                    // hasBackButton = navController.previousBackStackEntry != null, // Передаем флаг
                    onBackClick = { navController.navigateUp() },
                    // habitName = ... // Если это для конкретного экрана, то этот параметр нужно передавать динамически
                )
            }

        },
        containerColor = if (currentRoute != AppScreens.ONBOARDING) {
            AppColor.BgColorLightOrange
        } else {
            AppColor.White
        },
        modifier = Modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreens.ONBOARDING,//Routes.ONBOARDING.name,//if (uiState.onboardingFinished) Routes.VPN_HOME.name else Routes.ONBOARDING.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            onboardingScreen(navController)
            habitInfoScreen(navController)
            newHabitScreen(navController)
            homeScreen(navController)
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
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainAppScreen(
        modifier = Modifier
    )
}