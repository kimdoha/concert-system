package kr.hhplus.be.server.adapter.inbound.api.payment.request

import kr.hhplus.be.server.application.ports.inbound.payment.ReservationAndPaymentUseCase

/**
 * @author Doha Kim
 */
data class ReserveAndPayRequest (
    val userId: String,
    val scheduleId: String,
    val seatId: String,
) {
    fun toCommand() = ReservationAndPaymentUseCase.ReservationAndPaymentCommand(
        userId = userId,
        scheduleId = scheduleId,
        seatId = seatId,
    )
}
