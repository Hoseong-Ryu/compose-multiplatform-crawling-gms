package org.dvhs.crawling.ui.component.time

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.dvhs.crawling.domain.model.GameContent

@Composable
internal fun ResetTimerItem(
    content: GameContent,
    remainingSeconds: Long,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = content.name,
                    style = MaterialTheme.typography.h3
                )
                Text(
                    text = content.description,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.primary
                )
            }

            Text(
                text = formatRemainingTime(remainingSeconds),
                style = MaterialTheme.typography.h2,
                color = if (remainingSeconds < 3600) MaterialTheme.colors.error
                       else MaterialTheme.colors.primary
            )
        }
    }
}

private fun formatRemainingTime(seconds: Long): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val remainingSeconds = seconds % 60
    return "${hours}시간 ${minutes}분 ${remainingSeconds}초"
}