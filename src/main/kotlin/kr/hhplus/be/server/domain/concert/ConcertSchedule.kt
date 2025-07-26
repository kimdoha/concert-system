package kr.hhplus.be.server.domain.concert

import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
data class ConcertSchedule(
    val performanceStartTime: LocalDateTime,
    val performanceEndTime: LocalDateTime,
    val performers: List<String>,
    val totalSeatCnt: Int,
    val availableSeatCnt: Int,
    val status: ScheduleStatus,
    val seats: List<ConcertSeat> = emptyList(),
) {
    enum class ScheduleStatus {
        SCHEDULED, // 공연 일정이 예정됨
        OPEN, // 공연 일정이 오픈됨
        SOLD_OUT, // 공연 일정이 매진됨
    }
}
