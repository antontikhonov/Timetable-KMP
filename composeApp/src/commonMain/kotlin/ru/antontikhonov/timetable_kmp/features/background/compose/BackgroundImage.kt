package ru.antontikhonov.timetable_kmp.features.background.compose

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.rememberAsyncImagePainter
import org.jetbrains.compose.resources.painterResource
import ru.antontikhonov.timetable_kmp.features.background.BackgroundViewModel
import timetable_kmp.composeapp.generated.resources.Res
import timetable_kmp.composeapp.generated.resources.alina

@Composable
fun BackgroundImage(
    viewModel: BackgroundViewModel,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val painter = state?.let { url ->
        rememberAsyncImagePainter(
            model = url,
//            model = "https://corsproxy.io/?url=$url",
            fallback = painterResource(Res.drawable.alina),
            placeholder = ColorPainter(Color.Black)
        )
    } ?: painterResource(Res.drawable.alina)
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier,
    )
}
