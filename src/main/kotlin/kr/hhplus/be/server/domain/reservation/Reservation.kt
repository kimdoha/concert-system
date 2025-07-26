package kr.hhplus.be.server.domain.reservation

import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
data class Reservation(
    val reservationId: String,
    val userId: String,
    val scheduleId: Long,
    val seatId: Long,
    val reservationStatus: ReservationStatus,
    val totalAmount: BigDecimal,
    val reservedYmdt: LocalDateTime,
    val reservedUntil: LocalDateTime,
    val confirmedYmdt: LocalDateTime? = null,
) {
    enum class ReservationStatus {
        TEMP_RESERVED, CANCELLED, EXPIRED, CONFIRMED
    }
}
