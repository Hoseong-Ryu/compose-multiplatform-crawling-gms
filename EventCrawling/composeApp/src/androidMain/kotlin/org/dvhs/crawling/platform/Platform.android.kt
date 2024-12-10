package org.dvhs.crawling.platform

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone

class AndroidPlatform : PlatformTime() {

}

actual open class PlatformTime {
    actual fun getCurrentTimeZone(): TimeZone {
        return TimeZone.of(java.util.TimeZone.getDefault().id)
    }

    actual fun observeTime(callback: (Instant?) -> Unit) {
        // Android specific implementation
    }

    actual fun stopObserving() {
        // Android specific cleanup
    }
}