package org.dvhs.crawling.ui.component.time

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDateTime

@Composable
fun TimeDifference(
    currentLocalTime: LocalDateTime?,
    currentServerTime: LocalDateTime?,
){
    val hourDifference = (currentServerTime?.hour ?: 0) - (currentLocalTime?.hour ?: 0)
    Column {
        Text(
            text = "Difference",
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "$hourDifference hours",
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = createTimeDifferenceMessage(hourDifference),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
        )
    }
}

private fun createTimeDifferenceMessage(hourDifference: Int): String {
    return when {
        hourDifference > 0 -> "The server time is $hourDifference hours ahead of your local time."
        hourDifference < 0 -> "The server time is ${-hourDifference} hours behind your local time."
        else -> "The server time is the same as your local time."
    }
}