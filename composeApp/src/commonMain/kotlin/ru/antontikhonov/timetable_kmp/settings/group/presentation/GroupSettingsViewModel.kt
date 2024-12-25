package ru.antontikhonov.timetable_kmp.settings.group.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ru.antontikhonov.timetable_kmp.core.onError
import ru.antontikhonov.timetable_kmp.core.onSuccess
import ru.antontikhonov.timetable_kmp.timetable.domain.GroupsRepository

class GroupSettingsViewModel(
    private val groupsRepository: GroupsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(GroupSettingsState())
    val state = _state.asStateFlow()
        .onStart {
            loadGroups()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value,
        )

    fun onAction(action: GroupSettingsAction) {
        when (action) {
            is GroupSettingsAction.OnSearchQueryChange -> {
                val filteredGroups = if (action.query.isEmpty()) {
                    state.value.rawGroups
                } else {
                    val normilizedQuery = action.query.normilize()
                    state.value.rawGroups.filter { group ->
                        group.startsWith(normilizedQuery)
                    }
                }
                _state.update {
                    it.copy(
                        searchQuery = action.query,
                        filteredGroups = filteredGroups,
                    )
                }
            }
        }
    }

    private suspend fun loadGroups() {
        groupsRepository.getGroups()
            .onSuccess { groups ->
                _state.update {
                    it.copy(
                        rawGroups = groups,
                        filteredGroups = groups,
                        isLoading = false,
                        errorMessage = null,
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(isLoading = false, errorMessage = error)
                }
            }
    }

    private fun String.normilize(): String {
        return this.replace('C', 'С')  // latin С -> cyrillic С
            .replace('c', 'С')  // latin c -> cyrillic С
            .replace('с', 'С')  // cyrillic c -> cyrillic С
            .replace('M', 'М')  // latin M -> cyrillic М
            .replace('m', 'М')  // latin m -> cyrillic М
            .replace('м', 'М')  // cyrillic м -> cyrillic М
            .replace('B', 'Б')  // latin B -> cyrillic Б
            .replace('b', 'Б')  // latin b -> cyrillic Б
            .replace('б', 'Б')  // cyrillic б -> cyrillic Б
    }
}
