package org.dvhs.crawling.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun GameHelperTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Blue400,
            surface = Gray900,
            background = Gray900,
            onSurface = Color.White
        )
    } else {
        lightColors()
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}

private val Blue400 = Color(0xFF4589FF)
private val Gray900 = Color(0xFF141414)