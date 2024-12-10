package org.dvhs.crawling.platform

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import platform.Foundation.NSTimeZone
import platform.Foundation.defaultTimeZone

class IOSPlatform: PlatformTime() {

}

actual open class PlatformTime {
    actual fun getCurrentTimeZone(): TimeZone {
        return TimeZone.of(NSTimeZone.defaultTimeZone.name)
    }

    actual fun observeTime(callback: (Instant?) -> Unit) {
        // iOS specific implementation
    }

    actual fun stopObserving() {
        // iOS specific cleanup
    }
}