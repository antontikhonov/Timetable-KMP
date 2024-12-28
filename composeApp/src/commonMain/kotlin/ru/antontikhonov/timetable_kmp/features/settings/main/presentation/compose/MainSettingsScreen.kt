package ru.antontikhonov.timetable_kmp.features.settings.main.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import ru.antontikhonov.timetable_kmp.resources.Colors
import timetable_kmp.composeapp.generated.resources.Res
import timetable_kmp.composeapp.generated.resources.choice_group_button
import timetable_kmp.composeapp.generated.resources.choice_theme_button
import timetable_kmp.composeapp.generated.resources.ic_view_list
import timetable_kmp.composeapp.generated.resources.ic_wallpaper
import timetable_kmp.composeapp.generated.resources.settings

@Composable
internal fun MainSettingScreenRoot(
    onChangeGroupClick: () -> Unit,
) {
    MainSettingScreen(onChangeGroupClick = onChangeGroupClick)
}

@Composable
private fun MainSettingScreen(
    onChangeGroupClick: () -> Unit,
) {
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Colors.DARK_BLACK_TRANSPARENT)
                .statusBarsPadding()
                .padding(16.dp),
            text = stringResource(Res.string.settings),
            color = Color.White,
        )
        SettingsItem(
            iconResource = Res.drawable.ic_view_list,
            text = stringResource(Res.string.choice_group_button),
            modifier = Modifier.padding(16.dp),
            onClick = onChangeGroupClick,
        )
        SettingsItem(
            iconResource = Res.drawable.ic_wallpaper,
            text = stringResource(Res.string.choice_theme_button),
            modifier = Modifier.padding(horizontal = 16.dp),
        )
    }
}
