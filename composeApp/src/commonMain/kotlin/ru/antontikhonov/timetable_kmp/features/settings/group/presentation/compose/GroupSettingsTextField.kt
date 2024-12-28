package ru.antontikhonov.timetable_kmp.features.settings.group.presentation.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import ru.antontikhonov.timetable_kmp.features.settings.group.presentation.GroupSettingsAction
import ru.antontikhonov.timetable_kmp.features.settings.group.presentation.GroupSettingsState
import timetable_kmp.composeapp.generated.resources.Res
import timetable_kmp.composeapp.generated.resources.search_hint

@Composable
internal fun GroupTextField(
    state: GroupSettingsState,
    onAction: (GroupSettingsAction) -> Unit,
) {
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
