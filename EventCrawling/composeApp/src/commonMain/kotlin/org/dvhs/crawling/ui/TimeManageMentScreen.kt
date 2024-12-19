package org.dvhs.crawling.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.dvhs.crawling.ui.component.TimeDifference
import org.dvhs.crawling.ui.component.TimeSection
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
fun TimeScreen(
    viewModel: TimeViewModel = rememberTimeViewModel(),
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentTime by viewModel.currentTime.collectAsState()
    val currentServerTime = currentTime?.toLocalDateTime(TimeZone.UTC)
    val currentLocalTime = currentTime?.toLocalDateTime(TimeZone.of("Japan"))


    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(vertical = 8.dp)
        ) {
            // Header
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = onClose
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close"
                    )
                }
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Time",
                    style = MaterialTheme.typography.h6
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                // Server Time
                TimeSection(
                    title = "Server Time",
                    time = currentServerTime,
                    timeStyle = MaterialTheme.typography.h4
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Local Time
                TimeSection(
                    title = "Local Time",
                    time = currentLocalTime,
                    timeStyle = MaterialTheme.typography.h4
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Difference
                TimeDifference(currentLocalTime, currentServerTime)
            }
        }
    }
}