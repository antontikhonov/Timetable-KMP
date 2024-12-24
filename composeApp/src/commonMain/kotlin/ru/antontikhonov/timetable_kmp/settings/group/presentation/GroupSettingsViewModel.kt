package ru.antontikhonov.timetable_kmp.settings.group.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GroupSettingsViewModel : ViewModel() {

    private val _state = MutableStateFlow(GroupSettingsState())
    val state = _state.asStateFlow()

    fun onAction(action: GroupSettingsAction) {
        when (action) {
            is GroupSettingsAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }
        }
    }
}