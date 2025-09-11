package org.eventhorizon.habitify.ui.screens.main

import android.ruvpn.ui.common.components.CustomTopAppBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.eventhorizon.habitify.ui.navigation.Routes
import org.eventhorizon.habitify.ui.screens.onboarding.OnboardingScreen
import org.eventhorizon.habitify.ui.theme.AppColor

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
            CustomTopAppBar(
                currentScreen = currentScreen,
                onBackClick = {
                    if (navController.previousBackStackEntry != null) {
                        navController.navigateUp()
                    }
                },
                habitName = " "
            )
        },
        modifier = Modifier
            .background(AppColor.White)
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.ONBOARDING.name,//if (uiState.onboardingFinished) Routes.VPN_HOME.name else Routes.ONBOARDING.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ){
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
                            restoreState = true
                        }
                    })
            }
            composable(route = Routes.HOME.name) {

            }
            composable(route = Routes.HABIT_INFO.name) {

            }
            composable(route = Routes.NEW_HABIT.name) {

            }
        }
    }
}