package ru.antontikhonov.timetable_kmp.timetable.data.repository

import ru.antontikhonov.timetable_kmp.core.NetworkError
import ru.antontikhonov.timetable_kmp.core.Result
import ru.antontikhonov.timetable_kmp.core.map
import ru.antontikhonov.timetable_kmp.timetable.data.api.TimetableApiService
import ru.antontikhonov.timetable_kmp.timetable.domain.GroupsRepository

class GroupsRepositoryImpl(
    private val apiService: TimetableApiService,
) : GroupsRepository {

    override suspend fun getGroups(): Result<List<String>, NetworkError> {
        return apiService.getGroups().map { it.groups }
    }
}
