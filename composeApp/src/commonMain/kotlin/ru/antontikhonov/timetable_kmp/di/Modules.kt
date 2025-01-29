package ru.antontikhonov.timetable_kmp.di

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.observable.makeObservable
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.antontikhonov.timetable_kmp.core.data.HttpClientFactory
import ru.antontikhonov.timetable_kmp.data.api.TimetableApiService
import ru.antontikhonov.timetable_kmp.data.api.KtorTimetableApiService
import ru.antontikhonov.timetable_kmp.domain.repositores.TimetableRepository
import ru.antontikhonov.timetable_kmp.data.repository.TimetableRepositoryImpl
import ru.antontikhonov.timetable_kmp.features.timetable.presentation.TimetableViewModel
import ru.antontikhonov.timetable_kmp.features.settings.group.presentation.GroupSettingsViewModel
import ru.antontikhonov.timetable_kmp.domain.repositores.GroupsRepository
import ru.antontikhonov.timetable_kmp.data.repository.GroupsRepositoryImpl
import ru.antontikhonov.timetable_kmp.domain.repositores.GroupSettingsRepository
import ru.antontikhonov.timetable_kmp.data.repository.GroupSettingsRepositoryImpl
import ru.antontikhonov.timetable_kmp.data.repository.ThemeSettingsRepositoryImpl
import ru.antontikhonov.timetable_kmp.data.repository.ThemesRepositoryImpl
import ru.antontikhonov.timetable_kmp.domain.repositores.ThemeSettingsRepository
import ru.antontikhonov.timetable_kmp.domain.repositores.ThemesRepository
import ru.antontikhonov.timetable_kmp.features.settings.theme.ThemeSettingsViewModel
import ru.antontikhonov.timetable_kmp.features.background.BackgroundViewModel

expect val platformModule: Module

@OptIn(ExperimentalSettingsApi::class)
val sharedModule = module {
    single { Settings() }
    single { HttpClientFactory.create(get()) }
    single<ObservableSettings> { get<Settings>().makeObservable() }
    singleOf(::KtorTimetableApiService).bind<TimetableApiService>()
    singleOf(::TimetableRepositoryImpl).bind<TimetableRepository>()
    singleOf(::GroupsRepositoryImpl).bind<GroupsRepository>()
    singleOf(::GroupSettingsRepositoryImpl).bind<GroupSettingsRepository>()
    singleOf(::ThemeSettingsRepositoryImpl).bind<ThemeSettingsRepository>()
    singleOf(::ThemesRepositoryImpl).bind<ThemesRepository>()

    viewModelOf(::TimetableViewModel)
    viewModelOf(::GroupSettingsViewModel)
    viewModelOf(::ThemeSettingsViewModel)
    viewModelOf(::BackgroundViewModel)
}
