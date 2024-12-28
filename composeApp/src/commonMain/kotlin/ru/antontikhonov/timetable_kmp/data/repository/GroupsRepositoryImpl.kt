package ru.antontikhonov.timetable_kmp.data.repository

import ru.antontikhonov.timetable_kmp.core.NetworkError
import ru.antontikhonov.timetable_kmp.core.Result
import ru.antontikhonov.timetable_kmp.core.map
import ru.antontikhonov.timetable_kmp.data.api.TimetableApiService
import ru.antontikhonov.timetable_kmp.domain.repositores.GroupsRepository

class GroupsRepositoryImpl(
    private val apiService: TimetableApiService,
) : GroupsRepository {

    override suspend fun getGroups(): Result<List<String>, NetworkError> {
        return apiService.getGroups().map { it.groups }
    }
}
