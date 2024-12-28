package ru.antontikhonov.timetable_kmp.data.mappers

import ru.antontikhonov.timetable_kmp.data.api.entities.ThemeApi
import ru.antontikhonov.timetable_kmp.domain.entities.ThemeEntity

fun ThemeApi.toDomain(): ThemeEntity {
    return ThemeEntity(
        id = id,
        name = name,
        isDarkTheme = isDarkTheme,
        url = url,
    )
}
