package ru.antontikhonov.timetable_kmp.features.background

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import ru.antontikhonov.timetable_kmp.domain.repositores.ThemeSettingsRepository

class BackgroundViewModel(
    themeSettingsRepository: ThemeSettingsRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<String?>(null)
    val state = themeSettingsRepository.getThemeSettings()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value,
        )
}
