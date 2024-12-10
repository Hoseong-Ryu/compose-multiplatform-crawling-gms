package org.dvhs.crawling.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDateTime

@Composable
internal fun ServerTimeCard(
    serverTime: LocalDateTime?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "서버 시간 (GMT+0)",
                style = MaterialTheme.typography.h2
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = serverTime?.let {
                    "${it.year}-${it.monthNumber}-${it.dayOfMonth} " +
                    "${it.hour}:${it.minute}:${it.second}"
                } ?: "로딩중...",
                style = MaterialTheme.typography.h2
            )
        }
    }
}