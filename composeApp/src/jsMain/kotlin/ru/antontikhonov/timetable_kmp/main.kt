package ru.antontikhonov.timetable_kmp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import org.jetbrains.skiko.wasm.onWasmReady
import ru.antontikhonov.timetable_kmp.app.App
import ru.antontikhonov.timetable_kmp.di.initKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin()
    onWasmReady {
        CanvasBasedWindow("Timetable/JS") {
            App()
        }
    }
}
