package kr.hhplus.be.server.domain.payment

import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
data class Payment(
    val paymentId: String,
    val reservationId: String,
    val userId: String,
    val amount: BigDecimal,
    val paymentStatus: PaymentStatus,
    val paidYmdt: LocalDateTime,
)
