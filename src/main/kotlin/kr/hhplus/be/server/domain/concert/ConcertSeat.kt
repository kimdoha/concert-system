package kr.hhplus.be.server.domain.concert

import java.math.BigDecimal

/**
 * @author Doha Kim
 */
data class ConcertSeat(
    val seatNo: Int,
    val price: BigDecimal,
    val status: SeatStatus,
    val seatType: SeatType,
) {
    enum class SeatType {
        VIP, // VIP 좌석
        R, // R 좌석
        S, // S 좌석
        A, // A 좌석
    }

    enum class SeatStatus {
        AVAILABLE, // 좌석이 사용 가능
        TEMP_RESERVED, // 좌석이 임시 예약됨
        SOLD, // 좌석이 판매됨
    }
}
