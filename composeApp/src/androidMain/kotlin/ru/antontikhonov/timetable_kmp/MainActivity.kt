package ru.antontikhonov.timetable_kmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ru.antontikhonov.timetable_kmp.app.App
import ru.antontikhonov.timetable_kmp.resources.Colors

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                scrim = Colors.DARK_BLACK_TRANSPARENT.value.toInt(),
            )
        )
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}
