package ru.antontikhonov.timetable_kmp.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import ru.antontikhonov.timetable_kmp.resources.Colors
import ru.antontikhonov.timetable_kmp.settings.group.presentation.GroupSettingsViewModel
import ru.antontikhonov.timetable_kmp.settings.group.presentation.compose.GroupSettingScreenRoot
import ru.antontikhonov.timetable_kmp.settings.main.presentation.compose.MainSettingScreenRoot
import ru.antontikhonov.timetable_kmp.timetable.presentation.TimetableViewModel
import ru.antontikhonov.timetable_kmp.timetable.presentation.compose.TimetableScreenRoot
import timetable_kmp.composeapp.generated.resources.Res
import timetable_kmp.composeapp.generated.resources.alina
import timetable_kmp.composeapp.generated.resources.settings
import timetable_kmp.composeapp.generated.resources.timetable

@Composable
fun App() {
    var selectedTab by remember { mutableStateOf(0) }
    val navController = rememberNavController()
    MaterialTheme {
        Scaffold(
            backgroundColor = Color.Transparent,
            bottomBar = {
                BottomNavigation(
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp,
                    modifier = Modifier
                        .background(Colors.DARK_BLACK_TRANSPARENT)
                        .navigationBarsPadding()
                        .fillMaxWidth()
                ) {
                    TimetableBottomNavigationItem(
                        selected = selectedTab == 0,
                        labelText = stringResource(Res.string.timetable),
                        icon = Icons.AutoMirrored.Filled.List,
                        onClick = {
                            selectedTab = 0
                            navController.navigate(Route.Timetable)
                        },
                    )
                    TimetableBottomNavigationItem(
                        selected = selectedTab == 1,
                        labelText = stringResource(Res.string.settings),
                        icon = Icons.Default.Settings,
                        onClick = {
                            selectedTab = 1
                            navController.navigate(Route.MainSettings)
                        },
                    )
                }
            }
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(resource = Res.drawable.alina),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize(),
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.TimetableGraph,
                    ) {
                        navigation<Route.TimetableGraph>(
                            startDestination = Route.Timetable,
                        ) {
                            composable<Route.Timetable> {
                                TimetableScreenRoot(
                                    viewModel = koinViewModel<TimetableViewModel>()
                                )
                            }
                            composable<Route.MainSettings> {
                                MainSettingScreenRoot(
                                    onChangeGroupClick = {
                                        navController.navigate(Route.GroupSettings)
                                    }
                                )
                            }
                            composable<Route.GroupSettings> {
                                GroupSettingScreenRoot(
                                    viewModel = koinViewModel<GroupSettingsViewModel>(),
                                    onBackClick = {
                                        navController.popBackStack()
                                    },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
