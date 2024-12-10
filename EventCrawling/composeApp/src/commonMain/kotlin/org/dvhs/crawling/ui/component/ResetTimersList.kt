package org.dvhs.crawling.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.dvhs.crawling.domain.model.GameContent

@Composable
internal fun ResetTimersList(
    upcomingResets: List<Pair<GameContent, Long>>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "다가오는 컨텐츠 초기화",
            style = MaterialTheme.typography.h2
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(
                count = upcomingResets.size,
                key = { index -> upcomingResets[index].first.id },
                itemContent = { index ->
                    val (content, remainingSeconds) = upcomingResets[index]
                    ResetTimerItem(
                        content = content,
                        remainingSeconds = remainingSeconds,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            )
        }
    }
}