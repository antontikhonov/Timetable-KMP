package ru.antontikhonov.timetable_kmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import io.ktor.client.engine.okhttp.OkHttp
import ru.antontikhonov.timetable_kmp.networking.TimetableClient
import ru.antontikhonov.timetable_kmp.networking.createHttpClient
import ru.antontikhonov.timetable_kmp.presentation.App
import ru.antontikhonov.timetable_kmp.resources.Colors

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                scrim = Colors.DARK_BLACK_TRANSPARENT.value.toInt(),
            )
        )
        super.onCreate(savedInstanceState)

        setContent {
            App(
                client = remember {
                    TimetableClient(createHttpClient(OkHttp.create()))
                }
            )
        }
    }
}
