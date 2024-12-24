package ru.antontikhonov.timetable_kmp.settings.group.presentation

sealed interface GroupSettingsAction {

    data class OnSearchQueryChange(val query: String) : GroupSettingsAction
}