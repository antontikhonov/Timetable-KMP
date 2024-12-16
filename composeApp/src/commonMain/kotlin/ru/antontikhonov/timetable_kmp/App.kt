package ru.antontikhonov.timetable_kmp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import ru.antontikhonov.timetable_kmp.timetable.presentation.TimetableViewModel
import ru.antontikhonov.timetable_kmp.timetable.presentation.compose.TimetableScreenRoot
import timetable_kmp.composeapp.generated.resources.Res
import timetable_kmp.composeapp.generated.resources.alina

@Composable
fun App() {
    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(resource = Res.drawable.alina),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize(),
            )
            TimetableScreenRoot(
                viewModel = koinViewModel<TimetableViewModel>()
            )
        }
    }
}
