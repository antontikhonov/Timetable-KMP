package ru.antontikhonov.timetable_kmp.features.settings.theme

import ru.antontikhonov.timetable_kmp.core.NetworkError
import ru.antontikhonov.timetable_kmp.domain.entities.ThemeEntity

data class ThemeSettingState(
    val themes: List<ThemeEntity> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: NetworkError? = null,
)
