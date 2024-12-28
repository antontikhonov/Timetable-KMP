package ru.antontikhonov.timetable_kmp.domain.repositores

import ru.antontikhonov.timetable_kmp.core.NetworkError
import ru.antontikhonov.timetable_kmp.core.Result

interface GroupsRepository {

    suspend fun getGroups(): Result<List<String>, NetworkError>
}
