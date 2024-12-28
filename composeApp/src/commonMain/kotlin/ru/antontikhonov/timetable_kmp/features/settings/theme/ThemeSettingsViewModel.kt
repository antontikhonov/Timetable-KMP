package ru.antontikhonov.timetable_kmp.features.settings.theme

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
import ru.antontikhonov.timetable_kmp.domain.repositores.ThemesRepository

class ThemeSettingsViewModel(
    private val themesRepository: ThemesRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(ThemeSettingState())
    val state = _state.asStateFlow()
        .onStart {
            loadThemes()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    private suspend fun loadThemes() {
        themesRepository.getThemes()
            .onSuccess { themes ->
                _state.update {
                    it.copy(
                        themes = themes,
                    )
                }
            }.onError {
                // todo
            }
    }
}
