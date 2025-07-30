package kr.hhplus.be.server.adapter.outbound.persistence.concert

import kr.hhplus.be.server.application.ports.outbound.concert.ConcertQueryPort
import org.springframework.stereotype.Repository

/**
 * @author Doha Kim
 */
@Repository
class ConcertPersistenceAdapter(
    private val jpaConcertRepository: JpaConcertRepository,
    private val jpaConcertScheduleRepository: JpaConcertScheduleRepository,
    private val jpaConcertSeatRepository: JpaConcertSeatRepository,
) : ConcertQueryPort {
    override fun findConcerts(): List<ConcertEntity> {
        return jpaConcertRepository.findAll()
    }

    override fun findConcertByConcertId(concertId: String): ConcertEntity? {
        return jpaConcertRepository.findByConcertId(concertId)
    }

    override fun findConcertScheduleByScheduleId(scheduleId: String): ConcertScheduleEntity? {
        return jpaConcertScheduleRepository.findByScheduleId(scheduleId)
    }

    override fun findSeatBySeatIdWithLock(seatId: String): ConcertSeatEntity? {
        return jpaConcertSeatRepository.findBySeatId(seatId)
    }
}
