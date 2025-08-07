package kr.hhplus.be.server.application.ports.inbound.payment

import kr.hhplus.be.server.application.ports.inbound.concert.CheckAvailableReservationTargetUseCase
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
interface ReservationAndPaymentUseCase {
    fun reserveAndPay(command: ReservationAndPaymentCommand): ReservationAndPaymentResult

    data class ReservationAndPaymentCommand(
        val userId: String,
        val concertId: String,
        val scheduleId: String,
        val seatId: String,
    ) {
        fun toValidateReservationTargetCommand(): CheckAvailableReservationTargetUseCase.ValidateReservationTargetCommand {
            return CheckAvailableReservationTargetUseCase.ValidateReservationTargetCommand(
                concertId = concertId,
                scheduleId = scheduleId,
                seatId = seatId,
            )
        }
    }

    data class ReservationAndPaymentResult(
        val reservationId: String,
        val paymentId: String,
        val totalAmount: BigDecimal,
        val reservedYmdt: LocalDateTime,
        val reservedUntil: LocalDateTime,
        val paidYmdt: LocalDateTime,
    )
}
