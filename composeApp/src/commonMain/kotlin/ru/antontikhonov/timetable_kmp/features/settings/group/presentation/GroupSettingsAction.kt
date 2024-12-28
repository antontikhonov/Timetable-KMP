package ru.antontikhonov.timetable_kmp.features.settings.group.presentation

sealed interface GroupSettingsAction {

    data class OnSearchQueryChange(val query: String) : GroupSettingsAction

    data class OnGroupSelect(val group: String) : GroupSettingsAction
}
