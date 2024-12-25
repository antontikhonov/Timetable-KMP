package ru.antontikhonov.timetable_kmp.di

import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.antontikhonov.timetable_kmp.core.data.HttpClientFactory
import ru.antontikhonov.timetable_kmp.timetable.data.api.TimetableApiService
import ru.antontikhonov.timetable_kmp.timetable.data.api.KtorTimetableApiService
import ru.antontikhonov.timetable_kmp.timetable.domain.TimetableRepository
import ru.antontikhonov.timetable_kmp.timetable.data.repository.TimetableRepositoryImpl
import ru.antontikhonov.timetable_kmp.timetable.presentation.TimetableViewModel
import ru.antontikhonov.timetable_kmp.settings.group.presentation.GroupSettingsViewModel
import ru.antontikhonov.timetable_kmp.timetable.domain.GroupsRepository
import ru.antontikhonov.timetable_kmp.timetable.data.repository.GroupsRepositoryImpl
import ru.antontikhonov.timetable_kmp.timetable.domain.GroupSettingsRepository
import ru.antontikhonov.timetable_kmp.timetable.data.repository.GroupSettingsRepositoryImpl

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
