package ru.antontikhonov.timetable_kmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import io.ktor.client.engine.okhttp.OkHttp
import ru.antontikhonov.timetable_kmp.networking.TimetableClient
import ru.antontikhonov.timetable_kmp.networking.createHttpClient
import ru.antontikhonov.timetable_kmp.presentation.App

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
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
