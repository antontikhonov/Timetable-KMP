package ru.antontikhonov.timetable_kmp.domain.repositores

import kotlinx.coroutines.flow.Flow

interface ThemeSettingsRepository {

    fun getThemeSettings(): Flow<String?>

    fun putThemeSettings(value: String)
}
