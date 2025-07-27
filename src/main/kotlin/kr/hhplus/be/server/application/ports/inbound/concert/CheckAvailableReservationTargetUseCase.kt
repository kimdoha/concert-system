package kr.hhplus.be.server.application.ports.inbound.concert

import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.concert.ConcertSchedule
import kr.hhplus.be.server.domain.concert.ConcertSeat

/**
 * @author Doha Kim
 */
interface CheckAvailableReservationTargetUseCase {
    fun validateAndGetTargets(command: ValidateReservationTargetCommand): ValidatedReservationTarget

    data class ValidateReservationTargetCommand(
        val concertId: String,
        val scheduleId: String,
        val seatId: String,
    )

    data class ValidatedReservationTarget(
        val concert: Concert,
        val schedule: ConcertSchedule,
        val seat: ConcertSeat,
    )
}
