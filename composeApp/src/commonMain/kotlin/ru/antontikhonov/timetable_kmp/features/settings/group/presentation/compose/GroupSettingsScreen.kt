package ru.antontikhonov.timetable_kmp.features.settings.group.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.stringResource
import ru.antontikhonov.timetable_kmp.features.settings.group.presentation.GroupSettingsAction
import ru.antontikhonov.timetable_kmp.features.settings.group.presentation.GroupSettingsState
import ru.antontikhonov.timetable_kmp.features.settings.group.presentation.GroupSettingsViewModel
import ru.antontikhonov.timetable_kmp.resources.Colors
import timetable_kmp.composeapp.generated.resources.Res
import timetable_kmp.composeapp.generated.resources.choice_group_button

@Composable
internal fun GroupSettingScreenRoot(
    viewModel: GroupSettingsViewModel,
    onBackClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    GroupSettingScreenRoot(
        state = state,
        onAction = { action ->
            viewModel.onAction(action)
        },
        onBackClick = onBackClick,
    )
}

@Composable
private fun GroupSettingScreenRoot(
    state: GroupSettingsState,
    onAction: (GroupSettingsAction) -> Unit,
    onBackClick: () -> Unit,
) {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(Res.string.choice_group_button),
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
        GroupTextField(state = state, onAction = onAction)
        Divider(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(2.dp),

            color = Colors.DIRTY_YELLOW,
        )
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(state.filteredGroups) { group ->
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(25.dp))
                        .background(Colors.BLACK_TRANSPARENT)
                        .clickable {
                            onAction(GroupSettingsAction.OnGroupSelect(group))
                        }
                        .padding(16.dp),
                    color = Color.White,
                    text = group
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
