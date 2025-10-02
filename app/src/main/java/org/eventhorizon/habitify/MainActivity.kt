package org.eventhorizon.habitify

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import org.eventhorizon.habitify.main.MainAppScreen
import org.eventhorizon.habitify.ui.components.theme.HabitifyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HabitifyTheme {
                Surface(modifier = Modifier.Companion.fillMaxSize()) {
                    MainAppScreen(modifier = Modifier)
                }
            }
        }
    }
}