package org.dvhs.crawling.domain.model

import kotlinx.datetime.DayOfWeek


data class GameContent(
    val id: String,
    val name: String,
    val resetHour: Int, // 0-23
    val resetMinute: Int = 0, // 0-59
    val resetDayOfWeek: DayOfWeek? = null, // 주간 컨텐츠의 경우
    val description: String,
    val isEnabled: Boolean = true
)

