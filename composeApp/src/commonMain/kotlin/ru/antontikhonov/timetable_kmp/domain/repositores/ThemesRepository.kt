package ru.antontikhonov.timetable_kmp.domain.repositores

import ru.antontikhonov.timetable_kmp.core.NetworkError
import ru.antontikhonov.timetable_kmp.domain.entities.ThemeEntity
import ru.antontikhonov.timetable_kmp.core.Result

interface ThemesRepository {

    suspend fun getThemes(): Result<List<ThemeEntity>, NetworkError>
}
