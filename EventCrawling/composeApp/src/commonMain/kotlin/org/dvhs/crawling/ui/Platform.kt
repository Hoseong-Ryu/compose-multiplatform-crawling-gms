package org.dvhs.crawling

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform