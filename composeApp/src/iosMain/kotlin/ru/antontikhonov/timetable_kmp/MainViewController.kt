package ru.antontikhonov.timetable_kmp

import androidx.compose.ui.window.ComposeUIViewController
import io.ktor.client.engine.darwin.Darwin

fun MainViewController() = ComposeUIViewController {
    App(httpEngine = Darwin.create())
}
