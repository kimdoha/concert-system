package kr.hhplus.be.server.application.service.concert

import kr.hhplus.be.server.adapter.outbound.persistence.concert.toDomain
import kr.hhplus.be.server.application.ports.inbound.concert.GetAvailableConcertSchedulesUseCase
import kr.hhplus.be.server.application.ports.outbound.concert.ConcertQueryPort
import org.springframework.stereotype.Service

/**
 * @author Doha Kim
 */
@Service
class GetAvailableConcertSchedulesServiceImpl(
    private val concertQueryPort: ConcertQueryPort,
) : GetAvailableConcertSchedulesUseCase {
    override fun getConcerts(): GetAvailableConcertSchedulesUseCase.Output {
        // 1. 예약 가능한 공연 조회
        val concertEntities = concertQueryPort.findConcerts()

        // 2. 좌석 정보 포함 공연 상세 정보 조회
        val concerts = concertEntities.map { it.toDomain() }

        return GetAvailableConcertSchedulesUseCase.Output(concerts = concerts)
    }
}
