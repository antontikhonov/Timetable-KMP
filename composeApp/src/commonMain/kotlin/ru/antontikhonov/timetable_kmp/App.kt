package ru.antontikhonov.timetable_kmp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import io.ktor.client.engine.HttpClientEngine
import org.jetbrains.compose.resources.painterResource
import ru.antontikhonov.timetable_kmp.core.data.HttpClientFactory
import ru.antontikhonov.timetable_kmp.timetable.data.api.KtorTimetableApiService
import ru.antontikhonov.timetable_kmp.timetable.data.repository.TimetableRepositoryImpl
import ru.antontikhonov.timetable_kmp.timetable.presentation.TimetableViewModel
import ru.antontikhonov.timetable_kmp.timetable.presentation.compose.TimetableScreenRoot
import timetable_kmp.composeapp.generated.resources.Res
import timetable_kmp.composeapp.generated.resources.alina

@Composable
fun App(httpEngine: HttpClientEngine) {
    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(resource = Res.drawable.alina),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize(),
            )
            TimetableScreenRoot(
                viewModel = TimetableViewModel(
                    timetableRepository = TimetableRepositoryImpl(
                        apiService = KtorTimetableApiService(
                            httpClient = HttpClientFactory.create(httpEngine),
                        )
                    )
                )
            )
        }
    }
}
