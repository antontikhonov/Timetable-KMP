package ru.antontikhonov.timetable_kmp.features.settings.theme

import ru.antontikhonov.timetable_kmp.domain.entities.ThemeEntity

data class ThemeSettingState(
    val themes: List<ThemeEntity> = emptyList(),
)
