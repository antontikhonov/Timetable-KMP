package ru.antontikhonov.timetable_kmp

import androidx.compose.ui.window.ComposeUIViewController
import ru.antontikhonov.timetable_kmp.app.App
import ru.antontikhonov.timetable_kmp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}
