package ru.antontikhonov.timetable_kmp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.stringResource
import ru.antontikhonov.timetable_kmp.networking.TimetableClient
import ru.antontikhonov.timetable_kmp.networking.entities.TimetableEntity
import ru.antontikhonov.timetable_kmp.resources.Colors
import ru.antontikhonov.timetable_kmp.util.NetworkError
import ru.antontikhonov.timetable_kmp.util.onError
import ru.antontikhonov.timetable_kmp.util.onSuccess
import timetable_kmp.composeapp.generated.resources.Res
import timetable_kmp.composeapp.generated.resources.friday
import timetable_kmp.composeapp.generated.resources.monday
import timetable_kmp.composeapp.generated.resources.saturday
import timetable_kmp.composeapp.generated.resources.thursday
import timetable_kmp.composeapp.generated.resources.tuesday
import timetable_kmp.composeapp.generated.resources.wednesday

@Composable
internal fun TimetableScreen(client: TimetableClient) {
    var timetableEntity by remember { mutableStateOf<TimetableEntity?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<NetworkError?>(null) }

    LaunchedEffect(Unit) {
        isLoading = true
        error = null
        client.getTimetable()
            .onSuccess { timetableEntity = it }
            .onError { error = it }
        isLoading = false
    }

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabItems = listOf(
        stringResource(Res.string.monday),
        stringResource(Res.string.tuesday),
        stringResource(Res.string.wednesday),
        stringResource(Res.string.thursday),
        stringResource(Res.string.friday),
        stringResource(Res.string.saturday),
    )

    val pagerState = rememberPagerState {
        tabItems.size
    }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if(!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }

    Column {
        Box(
            modifier = Modifier
                .background(Colors.DARK_BLACK_TRANSPARENT)
                .statusBarsPadding(),
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                backgroundColor = Color.Transparent,
                contentColor = Color.White,
            ) {
                tabItems.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = {
                            selectedTabIndex = index
                        },
                        text = {
                            Text(
                                text = title,
                                fontSize = 12.sp,
                                color = if (selectedTabIndex == index) Colors.DIRTY_YELLOW else Color.White,
                            )
                        }
                    )
                }
            }
        }

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
            beyondViewportPageCount = 1,
            verticalAlignment = Alignment.Top,
        ) { page ->
            DayTabItem(
                dayEntity = timetableEntity?.days?.get(page),
                isLoading = isLoading,
                error = error,
            )
        }
    }
}
