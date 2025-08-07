package kr.hhplus.be.server.application.ports.inbound.payment

import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.concert.ConcertSchedule
import kr.hhplus.be.server.domain.concert.ConcertSeat
import kr.hhplus.be.server.domain.user.User
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
interface ReserveSeatUseCase {
    fun reserve(command: ReserveSeatCommand): Output

    data class ReserveSeatCommand(
        val concert: Concert,
        val schedule: ConcertSchedule,
        val seat: ConcertSeat,
        val user: User,
    )

    data class Output(
        val reservationId: String,
        val totalAmount: BigDecimal,
        val reservedYmdt: LocalDateTime,
        val reservedUntil: LocalDateTime,
    )
}
