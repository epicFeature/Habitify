package org.eventhorizon.habitify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import org.eventhorizon.habitify.ui.screens.main.MainScreen
import org.eventhorizon.habitify.ui.theme.HabitifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HabitifyTheme {
                Surface(modifier = Modifier.Companion.fillMaxSize()) {
                    MainScreen()
                }
            }
        }
    }
}