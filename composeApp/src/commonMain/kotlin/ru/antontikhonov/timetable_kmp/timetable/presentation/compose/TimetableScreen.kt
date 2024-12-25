package ru.antontikhonov.timetable_kmp.timetable.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import ru.antontikhonov.timetable_kmp.resources.Colors
import ru.antontikhonov.timetable_kmp.timetable.presentation.TimetableState
import ru.antontikhonov.timetable_kmp.timetable.presentation.TimetableViewModel
import timetable_kmp.composeapp.generated.resources.Res
import timetable_kmp.composeapp.generated.resources.error_message
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
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(25.dp),
                        strokeWidth = 2.dp,
                        color = Color.White,
                    )
                }
            } else if (state.errorMessage != null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        modifier = Modifier
                            .clip(RoundedCornerShape(25.dp))
                            .background(Colors.BLACK_TRANSPARENT)
                            .padding(16.dp),
                        text = stringResource(Res.string.error_message, state.errorMessage.name),
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                    )
                }
            } else {
                DayTabItem(
                    dayEntity = state.days.getOrNull(page),
                )
            }
        }
    }
}
