package ru.antontikhonov.timetable_kmp.features.timetable.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import ru.antontikhonov.timetable_kmp.features.commoncompose.ErrorTile
import ru.antontikhonov.timetable_kmp.features.commoncompose.LoadingTile
import ru.antontikhonov.timetable_kmp.features.timetable.presentation.TimetableState
import ru.antontikhonov.timetable_kmp.features.timetable.presentation.TimetableViewModel
import ru.antontikhonov.timetable_kmp.resources.Colors
import timetable_kmp.composeapp.generated.resources.Res
import timetable_kmp.composeapp.generated.resources.friday
import timetable_kmp.composeapp.generated.resources.monday
import timetable_kmp.composeapp.generated.resources.saturday
import timetable_kmp.composeapp.generated.resources.thursday
import timetable_kmp.composeapp.generated.resources.tuesday
import timetable_kmp.composeapp.generated.resources.wednesday

@Composable
fun TimetableScreenRoot(
    viewModel: TimetableViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    TimetableScreen(state)
}

@Composable
internal fun TimetableScreen(state: TimetableState) {
    val scope = rememberCoroutineScope()

    val tabItems = listOf(
        stringResource(Res.string.monday),
        stringResource(Res.string.tuesday),
        stringResource(Res.string.wednesday),
        stringResource(Res.string.thursday),
        stringResource(Res.string.friday),
        stringResource(Res.string.saturday),
    )

    val pagerState = rememberPagerState { tabItems.size }

    Column {
        Text(
            text = state.numberOfGroup,
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .background(Colors.DARK_BLACK_TRANSPARENT)
                .statusBarsPadding()
                .padding(start = 16.dp, top = 8.dp),
        )
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.Transparent,
            contentColor = Color.White,
            modifier = Modifier
                .background(Colors.DARK_BLACK_TRANSPARENT),
        ) {
            tabItems.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = title,
                            fontSize = 12.sp,
                            color = if (pagerState.currentPage == index) {
                                Colors.DIRTY_YELLOW
                            } else {
                                Color.White
                            },
                        )
                    }
                )
            }
        }

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
            beyondViewportPageCount = 1,
            verticalAlignment = Alignment.Top,
        ) { page ->
            if (state.isLoading) {
                LoadingTile()
            } else if (state.errorMessage != null) {
                ErrorTile(errorMessage = state.errorMessage.name)
            } else {
                DayTabItem(
                    dayEntity = state.days.getOrNull(page),
                )
            }
        }
    }
}
