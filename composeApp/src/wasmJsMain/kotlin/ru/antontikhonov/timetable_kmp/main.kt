package ru.antontikhonov.timetable_kmp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import io.ktor.client.engine.js.Js
import kotlinx.browser.document
import ru.antontikhonov.timetable_kmp.networking.TimetableClient
import ru.antontikhonov.timetable_kmp.networking.createHttpClient
import ru.antontikhonov.timetable_kmp.presentation.App

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        App(
            client = TimetableClient(createHttpClient(Js.create()))
        )
    }
}
