package org.dvhs.crawling

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.dvhs.crawling.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "EventCrawling",
    ) {
        App()
    }
}