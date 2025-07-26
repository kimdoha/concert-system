package kr.hhplus.be.server.application.ports.inbound.payment

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
    )

    data class ReservationAndPaymentResult(
        val reservationId: String,
        val paymentId: String,
        val totalAmount: String,
        val reservedYmdt: String,
        val reservedUntil: String,
    )
}
