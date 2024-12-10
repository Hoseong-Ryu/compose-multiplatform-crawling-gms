package org.dvhs.crawling.domain.model

import kotlinx.datetime.TimeZone

data class TimeZoneConfig(
    val serverTimeZone: TimeZone = TimeZone.UTC,
    val localTimeZone: TimeZone
)

