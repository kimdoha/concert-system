package kr.hhplus.be.server.adapter.outbound.persistence.concert

import kr.hhplus.be.server.application.ports.outbound.concert.ConcertQueryPort
import org.springframework.stereotype.Repository

/**
 * @author Doha Kim
 */
@Repository
class ConcertPersistenceAdapter(
    private val jpaConcertRepository: JpaConcertRepository,
) : ConcertQueryPort {
    override fun findConcerts(): List<ConcertEntity> {
        return jpaConcertRepository.findAll()
    }
}
