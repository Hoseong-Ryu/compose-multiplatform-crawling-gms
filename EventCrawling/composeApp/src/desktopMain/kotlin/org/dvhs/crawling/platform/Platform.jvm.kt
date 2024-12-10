package org.dvhs.crawling.platform

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone

class JVMPlatform: PlatformTime() {

}

actual open class PlatformTime {
    actual fun getCurrentTimeZone(): TimeZone {
        return TimeZone.of(java.util.TimeZone.getDefault().id)
    }

    actual fun observeTime(callback: (Instant?) -> Unit) {
        // Desktop specific implementation
    }

    actual fun stopObserving() {
        // Desktop specific cleanup
    }
}