package org.eventhorizon.habitify.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.eventhorizon.habitify.ui.navigation.Routes

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
}