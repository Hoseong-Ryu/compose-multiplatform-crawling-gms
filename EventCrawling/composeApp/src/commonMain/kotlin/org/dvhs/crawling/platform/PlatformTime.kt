package org.dvhs.crawling.platform

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone

expect open class PlatformTime() {
    fun getCurrentTimeZone(): TimeZone
    fun observeTime(callback: (Instant?) -> Unit)
    fun stopObserving()
}