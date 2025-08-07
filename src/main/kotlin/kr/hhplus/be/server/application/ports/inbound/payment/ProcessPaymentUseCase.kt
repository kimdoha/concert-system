package kr.hhplus.be.server.application.ports.inbound.payment

import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
interface ProcessPaymentUseCase {
    fun pay(command: PayCommand): Output

    data class PayCommand(
        val userId: String,
        val reservationId: String,
    )

    data class Output(
        val paymentId: String,
        val totalAmount: BigDecimal,
        val paidYmdt: LocalDateTime,
    )
}
