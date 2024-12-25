package ru.antontikhonov.timetable_kmp.timetable.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ru.antontikhonov.timetable_kmp.core.onError
import ru.antontikhonov.timetable_kmp.core.onSuccess
import ru.antontikhonov.timetable_kmp.timetable.domain.GroupSettingsRepository
import ru.antontikhonov.timetable_kmp.timetable.domain.TimetableRepository

class TimetableViewModel(
    private val timetableRepository: TimetableRepository,
    private val groupSettingsRepository: GroupSettingsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(TimetableState())
    val state = _state
        .onStart {
            val group = loadGroup()
            loadTimetable(group)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value,
        )

    private fun loadGroup(): String {
        val group = groupSettingsRepository.getGroupSettings()
        _state.update {
            it.copy(numberOfGroup = group)
        }
        return group
    }

    private suspend fun loadTimetable(group: String) {
        timetableRepository.getTimetable(groupNumber = group)
            .onSuccess { timetableResult ->
                _state.update {
                    it.copy(
                        days = timetableResult.days,
                        isLoading = false,
                        errorMessage = null,
                    )
                }
            }
            .onError { error ->
                _state.update { it.copy(isLoading = false, errorMessage = error) }
            }
    }
}
