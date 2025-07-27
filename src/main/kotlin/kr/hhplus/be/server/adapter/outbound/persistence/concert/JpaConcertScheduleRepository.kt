package kr.hhplus.be.server.adapter.outbound.persistence.concert

import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Doha Kim
 */
interface JpaConcertScheduleRepository : JpaRepository<ConcertScheduleEntity, Long> {
    fun findByScheduleId(scheduleId: String): ConcertScheduleEntity?
}
