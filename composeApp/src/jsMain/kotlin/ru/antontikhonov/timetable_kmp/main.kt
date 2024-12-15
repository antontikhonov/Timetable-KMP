package ru.antontikhonov.timetable_kmp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import io.ktor.client.engine.js.Js
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        CanvasBasedWindow("Timetable/JS") {
            App(httpEngine = Js.create())
        }
    }
}
