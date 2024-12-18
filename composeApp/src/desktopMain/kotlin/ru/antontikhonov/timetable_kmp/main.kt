package ru.antontikhonov.timetable_kmp

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ru.antontikhonov.timetable_kmp.app.App
import ru.antontikhonov.timetable_kmp.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Timetable",
            state = rememberWindowState(
                width = 420.dp,
                height = 720.dp,
            )
        ) {
            App()
        }
    }
}
