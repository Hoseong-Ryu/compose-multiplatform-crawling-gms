package org.dvhs.crawling.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.datetime.DayOfWeek
import org.dvhs.crawling.domain.model.GameContent
import org.dvhs.crawling.ui.screen.EventScreen
import org.dvhs.crawling.ui.screen.TimeScreen
import org.dvhs.crawling.ui.theme.GameHelperTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val sampleContents = listOf(
        GameContent(
            id = "daily-boss",
            name = "World Boss",
            resetHour = 6,  // 매일 06:00 UTC
            description = "Daily world boss raid",
            isEnabled = true
        ),
        GameContent(
            id = "weekly-raid",
            name = "Legion Raid",
            resetHour = 10,
            resetMinute = 30, // 매주 수요일 10:30 UTC
            resetDayOfWeek = DayOfWeek.WEDNESDAY,
            description = "Weekly legion raid reset",
            isEnabled = true
        ),
        GameContent(
            id = "daily-dungeon",
            name = "Chaos Dungeon",
            resetHour = 0, // 매일 00:00 UTC
            description = "Daily dungeon entry count reset",
            isEnabled = true
        )
    )
    GameHelperTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            var currentScreen by remember { mutableStateOf<NavigationScreen>(NavigationScreen.EventScreen) }
            when (currentScreen) {
                is NavigationScreen.TimeScreen -> TimeScreen(
                    onClose = { currentScreen = NavigationScreen.EventScreen }
                )
                is NavigationScreen.EventScreen -> EventScreen(
                    events = sampleContents,
                    onSetReminder = { currentScreen = NavigationScreen.TimeScreen }
                )
            }
        }
    }
}

sealed class NavigationScreen {
    data object TimeScreen : NavigationScreen()
    data object EventScreen : NavigationScreen()
}