package kr.hhplus.be.server.support.fixture

import kr.hhplus.be.server.adapter.outbound.persistence.concert.ConcertEntity
import kr.hhplus.be.server.adapter.outbound.persistence.concert.ConcertScheduleEntity
import kr.hhplus.be.server.domain.concert.ConcertSchedule
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
object ConcertScheduleFixture {
    fun create(
        concertEntity: ConcertEntity,
        performers: List<String> = listOf("Test Artist"),
        totalSeatCnt: Int = 100,
        availableSeatCnt: Int = 100,
        status: ConcertSchedule.ScheduleStatus = ConcertSchedule.ScheduleStatus.OPEN,
    ): ConcertScheduleEntity {
        val schedule = ConcertScheduleEntity(
            concert = concertEntity,
            performanceStartTime = LocalDateTime.parse("2025-07-26T19:00:00"),
            performanceEndTime = LocalDateTime.parse("2025-07-26T21:00:00"),
            performers = performers,
            totalSeatCnt = totalSeatCnt,
            availableSeatCnt = availableSeatCnt,
            status = status,
            seats = emptyList()
        )

        val seats = listOf(
            ConcertSeatFixture.create(concertSchedule = schedule)
        )

        schedule.seats = seats
        return schedule
    }
}
