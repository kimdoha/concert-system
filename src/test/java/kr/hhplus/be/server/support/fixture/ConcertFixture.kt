package kr.hhplus.be.server.support.fixture

import kr.hhplus.be.server.adapter.outbound.persistence.concert.ConcertEntity
import kr.hhplus.be.server.adapter.outbound.persistence.concert.ConcertScheduleEntity
import kr.hhplus.be.server.domain.concert.Concert
import java.util.*

/**
 * @author Doha Kim
 */
object ConcertFixture {
    fun create(
        title: String = "Test Concert",
        description: String = "This is a test concert.",
        location: String = "Test Venue",
        status: Concert.ConcertStatus = Concert.ConcertStatus.OPEN,
    ): ConcertEntity {
        val concert = ConcertEntity(
            concertId = UUID.randomUUID().toString(),
            title = title,
            description = description,
            location = location,
            status = status,
            schedules = emptyList(),
        )

        val schedules = listOf(
            ConcertScheduleFixture.create(concertEntity = concert)
        )
        concert.setSchedules(schedules)

        return concert
    }
}
