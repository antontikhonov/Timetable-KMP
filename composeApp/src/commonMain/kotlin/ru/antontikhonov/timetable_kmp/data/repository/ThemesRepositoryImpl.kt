package ru.antontikhonov.timetable_kmp.data.repository

import ru.antontikhonov.timetable_kmp.core.NetworkError
import ru.antontikhonov.timetable_kmp.core.map
import ru.antontikhonov.timetable_kmp.data.api.TimetableApiService
import ru.antontikhonov.timetable_kmp.data.mappers.toDomain
import ru.antontikhonov.timetable_kmp.domain.entities.ThemeEntity
import ru.antontikhonov.timetable_kmp.domain.repositores.ThemesRepository
import ru.antontikhonov.timetable_kmp.core.Result

internal class ThemesRepositoryImpl(
    private val apiService: TimetableApiService,
) : ThemesRepository {

    override suspend fun getThemes(): Result<List<ThemeEntity>, NetworkError> {
        return apiService.getThemes().map { themesResponse ->
            themesResponse.themes.map { themeApi -> themeApi.toDomain() }
        }
    }
}
