package ru.antontikhonov.timetable_kmp.features.settings.theme.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.rememberAsyncImagePainter
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.antontikhonov.timetable_kmp.features.settings.theme.ThemeSettingState
import ru.antontikhonov.timetable_kmp.features.settings.theme.ThemeSettingsViewModel
import ru.antontikhonov.timetable_kmp.resources.Colors
import timetable_kmp.composeapp.generated.resources.Res
import timetable_kmp.composeapp.generated.resources.alina
import timetable_kmp.composeapp.generated.resources.choice_theme_button

@Composable
fun ThemeSettingScreenRoot(
    viewModel: ThemeSettingsViewModel,
    onBackClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ThemeSettingScreen(
        state = state,
        onBackClick = onBackClick,
    )
}

@Composable
private fun ThemeSettingScreen(
    state: ThemeSettingState,
    onBackClick: () -> Unit,
) {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(Res.string.choice_theme_button),
                    color = Color.White,
                    fontSize = 14.sp,
                )
            },
            modifier = Modifier
                .background(Colors.DARK_BLACK_TRANSPARENT)
                .statusBarsPadding(),
            navigationIcon = {
                IconButton(
                    onClick = { onBackClick() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            },
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(state.themes) { themeItem ->
                Column {
                    var imageLoadResult by remember {
                        mutableStateOf<Result<Painter>?>(null)
                    }
                    val painter = rememberAsyncImagePainter(
                        model = themeItem.url,
                        onSuccess = {
                            imageLoadResult =
                                if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                                    Result.success(it.painter)
                                } else {
                                    Result.failure(Exception("Invalid image size"))
                                }
                        },
                        onError = {
                            it.result.throwable.printStackTrace()
                            imageLoadResult = Result.failure(it.result.throwable)
                        }
                    )
                    when (val result = imageLoadResult) {
                        null -> CircularProgressIndicator()
                        else -> {
                            Image (
                                painter = if (result.isSuccess) {
                                    painter
                                } else {
                                    painterResource(Res.drawable.alina)
                                },
                                contentDescription = themeItem.name,
                                contentScale = if (result.isSuccess) {
                                    ContentScale.Crop
                                } else {
                                    ContentScale.Fit
                                },
                                modifier = Modifier
                                    .aspectRatio(0.65f, matchHeightConstraintsFirst = true),
                            )
                        }
                    }
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = themeItem.name,
                        fontSize = 12.sp,
                        color = Color.White,
                    )
                }
            }
        }
    }
}
