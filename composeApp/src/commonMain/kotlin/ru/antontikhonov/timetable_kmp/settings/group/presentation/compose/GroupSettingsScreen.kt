package ru.antontikhonov.timetable_kmp.settings.group.presentation.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.stringResource
import ru.antontikhonov.timetable_kmp.resources.Colors
import ru.antontikhonov.timetable_kmp.settings.group.presentation.GroupSettingsAction
import ru.antontikhonov.timetable_kmp.settings.group.presentation.GroupSettingsState
import ru.antontikhonov.timetable_kmp.settings.group.presentation.GroupSettingsViewModel
import timetable_kmp.composeapp.generated.resources.Res
import timetable_kmp.composeapp.generated.resources.choice_group_button
import timetable_kmp.composeapp.generated.resources.search_hint

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
        val keyboardController = LocalSoftwareKeyboardController.current
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                onAction(GroupSettingsAction.OnSearchQueryChange(it))
            },
            shape = RoundedCornerShape(100),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = Color.Black,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                backgroundColor = Color.White.copy(alpha = 0.66f),
            ),
            placeholder = {
                Text(
                    text = stringResource(Res.string.search_hint),
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
            ),
            trailingIcon = {
                AnimatedVisibility(
                    visible = state.searchQuery.isNotBlank(),
                ) {
                    IconButton(
                        onClick = {
                            onAction(GroupSettingsAction.OnSearchQueryChange(""))
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .minimumInteractiveComponentSize(),
        )
    }
}