package org.dvhs.crawling.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDateTime

@Composable
fun TimeSection(
    title: String,
    time: LocalDateTime?,
    timeStyle: TextStyle,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = time?.formatWithPadding() ?: "Loading...",
            style = timeStyle
        )
    }
}

fun LocalDateTime.formatWithPadding(): String {
    return "${year}-" +
            "${monthNumber.toString().padStart(2, '0')}-" +
            "${dayOfMonth.toString().padStart(2, '0')} " +
            "${hour.toString().padStart(2, '0')}:" +
            "${minute.toString().padStart(2, '0')}:" +
            second.toString().padStart(2, '0')
}
