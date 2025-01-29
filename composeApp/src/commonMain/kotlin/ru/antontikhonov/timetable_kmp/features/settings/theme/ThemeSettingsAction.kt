package ru.antontikhonov.timetable_kmp.features.settings.theme

sealed interface ThemeSettingsAction {

    data class OnThemeSelect(val themeUrl: String) : ThemeSettingsAction
}
