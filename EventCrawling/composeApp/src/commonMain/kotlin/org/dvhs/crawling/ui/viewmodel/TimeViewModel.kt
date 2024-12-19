package org.dvhs.crawling.ui.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.until
import org.dvhs.crawling.domain.model.GameContent

class TimeViewModel {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _currentTime = MutableStateFlow<Instant?>(null)
    val currentTime: StateFlow<Instant?> = _currentTime.asStateFlow()

    private val _gameContents = MutableStateFlow<List<GameContent>>(emptyList())
    val gameContents: StateFlow<List<GameContent>> = _gameContents.asStateFlow()

    private val _upcomingResets = MutableStateFlow<List<Pair<GameContent, Long>>>(emptyList())
    val upcomingResets: StateFlow<List<Pair<GameContent, Long>>> = _upcomingResets.asStateFlow()

    private var timerJob: Job? = null

    init {
        startTimer()
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = scope.launch {
            while (isActive) {
                updateCurrentTime()
                updateUpcomingResets()
                delay(1000)
            }
        }
    }

    private fun updateCurrentTime() {
        _currentTime.value = Clock.System.now()
    }

    private fun calculateNextReset(content: GameContent, currentTime: LocalDateTime): LocalDateTime {
        // 현재 날짜의 리셋 시간 계산
        var resetDate = currentTime.date
        val resetTime = LocalTime(content.resetHour, content.resetMinute)
        var resetDateTime = LocalDateTime(resetDate, resetTime)

        // UTC 기준으로 변환하여 비교
        val currentInstant = currentTime.toInstant(TimeZone.UTC)
        var resetInstant = resetDateTime.toInstant(TimeZone.UTC)

        if (content.resetDayOfWeek != null) {
            // 주간 리셋
            var daysUntilReset = (content.resetDayOfWeek.ordinal - currentTime.dayOfWeek.ordinal).let {
                if (it <= 0) it + 7 else it
            }
            if (daysUntilReset == 0 && currentInstant > resetInstant) {
                daysUntilReset = 7
            }

            // 날짜만 계산
            resetDate = resetDate.plus(daysUntilReset, DateTimeUnit.DAY)
            resetDateTime = LocalDateTime(resetDate, resetTime)
        } else {
            // 일일 리셋
            if (currentInstant > resetInstant) {
                resetDate = resetDate.plus(1, DateTimeUnit.DAY)
                resetDateTime = LocalDateTime(resetDate, resetTime)
            }
        }

        return resetDateTime
    }

    private fun updateUpcomingResets() {
        val currentInstant = _currentTime.value ?: return
        val currentServerDateTime = currentInstant.toLocalDateTime(TimeZone.UTC)
        val contents = _gameContents.value

        _upcomingResets.value = contents
            .map { content ->
                val nextReset = calculateNextReset(content, currentServerDateTime)
                val duration = currentServerDateTime.toInstant(TimeZone.UTC)
                    .until(nextReset.toInstant(TimeZone.UTC), DateTimeUnit.SECOND)
                content to duration
            }
            .sortedBy { it.second }
    }

    fun addContent(content: GameContent) {
        _gameContents.value += content
    }

    fun removeContent(contentId: String) {
        _gameContents.value = _gameContents.value.filter { it.id != contentId }
    }

    fun onCleared() {
        timerJob?.cancel()
        scope.cancel()
    }

    // StateFlow를 각 플랫폼의 네이티브 상태 관리 시스템으로 변환하는 메서드들
    fun collectCurrentServerTime(callback: (Instant?) -> Unit) {
        scope.launch {
            currentTime.collect { callback(it) }
        }
    }

    fun collectUpcomingResets(callback: (List<Pair<GameContent, Long>>) -> Unit) {
        scope.launch {
            upcomingResets.collect { callback(it) }
        }
    }
}