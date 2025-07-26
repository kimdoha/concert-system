package kr.hhplus.be.server.adapter.inbound.api.payment.response

import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
data class ReserveAndPayResponse(
    val success: Boolean = true,
    val data: ReserveAndPayResponseData? = null,
) {
    data class ReserveAndPayResponseData(
        val reservationId: String,
        val userId: String,

        val concert: ReservedConcertInfo,
        val schedule: ReservedScheduleInfo,
        val seat: ReservedSeatInfo,
        val payment: PaymentInfo,
    ) {
        companion object {
            fun of(
                reservationId: String,
                userId: String,
                concert: ReservedConcertInfo,
                schedule: ReservedScheduleInfo,
                seat: ReservedSeatInfo,
                payment: PaymentInfo,
            ): ReserveAndPayResponseData {
                return ReserveAndPayResponseData(
                    reservationId = reservationId,
                    userId = userId,
                    concert = concert,
                    schedule = schedule,
                    seat = seat,
                    payment = payment
                )
            }
        }
    }

    data class ReservedSeatInfo(
        val seatId: String,
        val seatNo: Int,
        val price: BigDecimal,
        val seatType: String,
        val reservationStatus: String,
        val reservedAt: LocalDateTime,
        val reservedUntil: LocalDateTime,
    )

    data class ReservedConcertInfo(
        val concertId: String,
        val title: String,
        val description: String,
        val location: String,
    )

    data class ReservedScheduleInfo(
        val scheduleId: String,
        val performanceStartTime: LocalDateTime,
        val performanceEndTime: LocalDateTime,
        val performers: List<String>,
        val totalSeatCnt: Int,
        val availableSeatCnt: Int,
        val status: String,
    )

    data class PaymentInfo(
        val paymentId: String,
        val amount: BigDecimal,
        val paymentMethod: String,
        val paymentStatus: String,
        val paidAt: LocalDateTime,
    ) {
        companion object {
            fun of(reservationId: String, userId: String, data: ReserveAndPayResponseData): ReserveAndPayResponse {
                return ReserveAndPayResponse(
                    success = true,
                    data = ReserveAndPayResponseData(
                        reservationId = reservationId,
                        userId = userId,
                        concert = data.concert,
                        schedule = data.schedule,
                        seat = data.seat,
                        payment = data.payment
                    )
                )
            }
        }
    }
}
