package ru.antontikhonov.timetable_kmp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import io.ktor.client.engine.js.Js
import org.jetbrains.skiko.wasm.onWasmReady
import ru.antontikhonov.timetable_kmp.networking.TimetableClient
import ru.antontikhonov.timetable_kmp.networking.createHttpClient
import ru.antontikhonov.timetable_kmp.presentation.App

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        CanvasBasedWindow("Timetable/JS") {
            App(
                client = TimetableClient(createHttpClient(Js.create()))
            )
        }
    }
}
