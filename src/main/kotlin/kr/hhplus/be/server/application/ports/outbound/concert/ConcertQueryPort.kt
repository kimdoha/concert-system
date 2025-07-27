package kr.hhplus.be.server.application.ports.outbound.concert

import kr.hhplus.be.server.adapter.outbound.persistence.concert.ConcertEntity
import kr.hhplus.be.server.adapter.outbound.persistence.concert.ConcertScheduleEntity
import kr.hhplus.be.server.adapter.outbound.persistence.concert.ConcertSeatEntity

/**
 * @author Doha Kim
 */
interface ConcertQueryPort {
    fun findConcerts(): List<ConcertEntity>
    fun findConcertByConcertId(concertId: String): ConcertEntity?
    fun findConcertScheduleByScheduleId(scheduleId: String): ConcertScheduleEntity?
    fun findSeatBySeatId(seatId: String): ConcertSeatEntity?
}
