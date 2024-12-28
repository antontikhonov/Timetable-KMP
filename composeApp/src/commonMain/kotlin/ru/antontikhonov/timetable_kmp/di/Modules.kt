package ru.antontikhonov.timetable_kmp.di

import com.russhwolf.settings.Settings
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

expect val platformModule: Module

val sharedModule = module {
    single { Settings() }
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorTimetableApiService).bind<TimetableApiService>()
    singleOf(::TimetableRepositoryImpl).bind<TimetableRepository>()
    singleOf(::GroupsRepositoryImpl).bind<GroupsRepository>()
    singleOf(::GroupSettingsRepositoryImpl).bind<GroupSettingsRepository>()

    viewModelOf(::TimetableViewModel)
    viewModelOf(::GroupSettingsViewModel)
}
