package ru.antontikhonov.timetable_kmp

import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import io.ktor.client.engine.okhttp.OkHttp
import ru.antontikhonov.timetable_kmp.networking.TimetableClient
import ru.antontikhonov.timetable_kmp.networking.createHttpClient
import ru.antontikhonov.timetable_kmp.presentation.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Timetable",
        state = rememberWindowState(
            width = 420.dp,
            height = 720.dp,
        )
    ) {
        App(
            client = remember {
                TimetableClient(createHttpClient(OkHttp.create()))
            }
        )
    }
}
