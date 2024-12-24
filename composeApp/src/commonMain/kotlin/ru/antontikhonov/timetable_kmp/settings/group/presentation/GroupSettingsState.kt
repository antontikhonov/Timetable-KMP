package ru.antontikhonov.timetable_kmp.settings.group.presentation

import ru.antontikhonov.timetable_kmp.core.NetworkError

data class GroupSettingsState(
    val searchQuery: String = "",
    val rawGroups: List<String> = emptyList(),
    val filteredGroups: List<String> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: NetworkError? = null,
)
