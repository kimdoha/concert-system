package kr.hhplus.be.server.application.ports.inbound.concert

import kr.hhplus.be.server.domain.concert.Concert

/**
 * @author Doha Kim
 */
interface GetAvailableConcertSchedulesUseCase {
    fun getConcerts(): Output

    data class Output(
        val concerts: List<Concert>,
    )
}
