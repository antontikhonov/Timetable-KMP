package ru.antontikhonov.timetable_kmp.di

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

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorTimetableApiService).bind<TimetableApiService>()
    singleOf(::TimetableRepositoryImpl).bind<TimetableRepository>()

    viewModelOf(::TimetableViewModel)
}
