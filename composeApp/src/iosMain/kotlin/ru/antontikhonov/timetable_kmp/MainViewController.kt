package ru.antontikhonov.timetable_kmp

import androidx.compose.ui.window.ComposeUIViewController
import io.ktor.client.engine.darwin.Darwin
import ru.antontikhonov.timetable_kmp.networking.TimetableClient
import ru.antontikhonov.timetable_kmp.networking.createHttpClient
import ru.antontikhonov.timetable_kmp.presentation.App

fun MainViewController() = ComposeUIViewController {
    App(
        client = TimetableClient(createHttpClient(Darwin.create()))
    )
}
