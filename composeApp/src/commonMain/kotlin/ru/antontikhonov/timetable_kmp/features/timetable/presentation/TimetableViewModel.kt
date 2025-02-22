package ru.antontikhonov.timetable_kmp.features.timetable.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ru.antontikhonov.timetable_kmp.core.onError
import ru.antontikhonov.timetable_kmp.core.onSuccess
import ru.antontikhonov.timetable_kmp.domain.repositores.CurrentTimeProvider
import ru.antontikhonov.timetable_kmp.domain.repositores.GroupSettingsRepository
import ru.antontikhonov.timetable_kmp.domain.repositores.TimetableRepository

class TimetableViewModel(
    private val timetableRepository: TimetableRepository,
    private val groupSettingsRepository: GroupSettingsRepository,
    private val currentTimeProvider: CurrentTimeProvider,
) : ViewModel() {

    private val _state = MutableStateFlow(TimetableState())
    val state = _state
        .onStart {
            val group = loadGroup()
            loadTimetable(group)
            updateCurrentDate()
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

    private fun updateCurrentDate() {
        val localDate = currentTimeProvider()

        val currentMonthIndex = localDate.dayOfWeek.ordinal
        val day = localDate.dayOfMonth.toString().padStart(2, '0') // "01", "23"
        val month = localDate.monthNumber.toString().padStart(2, '0') // "01", "12"
        val year = localDate.year

        val formattedDate =  ", $day.$month.$year"

        _state.update { it.copy(currentDate = formattedDate, currentMonthIndex = currentMonthIndex) }
    }
}
