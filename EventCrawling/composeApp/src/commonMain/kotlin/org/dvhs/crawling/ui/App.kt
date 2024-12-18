package org.dvhs.crawling.ui

import androidx.compose.runtime.Composable
import org.dvhs.crawling.ui.theme.GameHelperTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    GameHelperTheme {
        TimeScreen(
            onClose = {}
        )
    }
}