package org.dvhs.crawling.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.dvhs.crawling.ui.component.ResetTimersList
import org.dvhs.crawling.ui.component.ServerTimeCard
import org.dvhs.crawling.ui.viewmodel.TimeViewModel

@Composable
fun rememberTimeViewModel(): TimeViewModel {
    val timeViewModel = remember { TimeViewModel() }

    DisposableEffect(timeViewModel) {
        onDispose {
            timeViewModel.onCleared()
        }
    }

    return timeViewModel
}

@Composable
fun TimeManagementScreen(
    viewModel: TimeViewModel = rememberTimeViewModel()
) {
    val currentServerTime by viewModel.currentServerTime.collectAsState()
    val upcomingResets by viewModel.upcomingResets.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ServerTimeCard(
            serverTime = currentServerTime?.toLocalDateTime(TimeZone.UTC),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        ResetTimersList(
            upcomingResets = upcomingResets,
            modifier = Modifier.fillMaxWidth()
        )
    }
}