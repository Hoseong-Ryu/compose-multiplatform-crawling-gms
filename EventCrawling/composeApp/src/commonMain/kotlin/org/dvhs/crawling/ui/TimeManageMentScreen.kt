package org.dvhs.crawling.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close"
                    )
                }
                Text(
                    text = "Time",
                    style = MaterialTheme.typography.h6
                )
                // 오른쪽 여백을 위한 투명 아이콘
                Spacer(modifier = Modifier.width(48.dp))
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Local Time
            TimeSection(
                title = "Local Time",
                time = "11:52 AM",
                timeStyle = MaterialTheme.typography.h4
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Server Time
            TimeSection(
                title = "Server Time",
                time = "19:52 PM",
                timeStyle = MaterialTheme.typography.h4
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Difference
            Column {
                Text(
                    text = "Difference",
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "-8 hours",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "The server time is 8 hours ahead of your local time.",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}