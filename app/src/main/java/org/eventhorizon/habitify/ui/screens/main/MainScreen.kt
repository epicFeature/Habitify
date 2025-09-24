package org.eventhorizon.habitify.ui.screens.main

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.eventhorizon.habitify.ui.navigation.Routes
import org.eventhorizon.habitify.ui.screens.habitinfo.HabitInfoScreen
import org.eventhorizon.habitify.ui.screens.home.HomeScreen
import org.eventhorizon.habitify.ui.screens.newhabit.NewHabitScreen
import org.eventhorizon.habitify.ui.screens.onboarding.OnboardingScreen
import org.eventhorizon.habitify.ui.theme.AppColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel() //hiltViewModel
) {
    val context = LocalContext.current

    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Routes.valueOf(
        backStackEntry?.destination?.route ?: Routes.ONBOARDING.name
    )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    Scaffold(
        topBar = {
            if (currentScreen != Routes.ONBOARDING) {
                CustomTopAppBar(
                    currentScreen = currentScreen,
                    onBackClick = {
                        if (navController.previousBackStackEntry != null) {
                            navController.navigateUp()
                        }
                    },
                    habitName = " "
                )
            }
        },
        containerColor = if (currentScreen != Routes.ONBOARDING) {
            AppColor.BgColorLightOrange
        } else {
            AppColor.White
        },
        modifier = Modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.ONBOARDING.name,//if (uiState.onboardingFinished) Routes.VPN_HOME.name else Routes.ONBOARDING.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = Routes.ONBOARDING.name) { //todo тщательно продумать всю навигацию и переделать. Это копипаст почти
                OnboardingScreen(
                    modifier = Modifier,
                    onSkipClick = {
                        viewModel.handleEvent(MainContract.MainUiEvent.OnOnboardingFinished)
                        navController.navigate(Routes.HOME.name) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                        }
                    })
            }
            composable(route = Routes.HOME.name) {
                HomeScreen(
                    modifier = Modifier,
                    onPlusClick = {
                        navController.navigate(Routes.NEW_HABIT.name) {
                            launchSingleTop = true
                        }
                    },
                    onHabitCardClick = {
                        navController.navigate(Routes.HABIT_INFO.name) {
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(route = Routes.HABIT_INFO.name) {
                HabitInfoScreen(
                    modifier = Modifier
                )
            }
            composable(route = Routes.NEW_HABIT.name) {
                NewHabitScreen(modifier = Modifier)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen()
}