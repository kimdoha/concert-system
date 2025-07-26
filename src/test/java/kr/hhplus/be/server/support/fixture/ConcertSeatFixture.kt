package kr.hhplus.be.server.support.fixture

import kr.hhplus.be.server.adapter.outbound.persistence.concert.ConcertScheduleEntity
import kr.hhplus.be.server.adapter.outbound.persistence.concert.ConcertSeatEntity
import kr.hhplus.be.server.domain.concert.ConcertSeat
import java.math.BigDecimal

/**
 * @author Doha Kim
 */
object ConcertSeatFixture {
    fun create(
        concertSchedule: ConcertScheduleEntity,
        seatNo: Int = 1,
        price: BigDecimal = BigDecimal(50000),
        status: ConcertSeat.SeatStatus = ConcertSeat.SeatStatus.AVAILABLE,
        type: ConcertSeat.SeatType = ConcertSeat.SeatType.R,
    ): ConcertSeatEntity {
        return ConcertSeatEntity(
            concertSchedule = concertSchedule,
            seatNo = seatNo,
            price = price,
            status = status,
            type = type,
        )
    }
}
