package kr.hhplus.be.server.application.ports.outbound.concert

import kr.hhplus.be.server.adapter.outbound.persistence.concert.ConcertEntity

/**
 * @author Doha Kim
 */
interface ConcertQueryPort {
    fun findConcerts(): List<ConcertEntity>
}
