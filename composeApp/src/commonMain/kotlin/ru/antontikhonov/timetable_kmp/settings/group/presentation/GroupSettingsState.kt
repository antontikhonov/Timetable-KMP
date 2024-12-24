package ru.antontikhonov.timetable_kmp.settings.group.presentation

data class GroupSettingsState(
    val searchQuery: String = "",
    val searchResults: List<String> = emptyList(),
    val isLoading: Boolean = false,
)
