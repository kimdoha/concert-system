package kr.hhplus.be.server.application.service.payment

import kr.hhplus.be.server.application.ports.inbound.payment.ReserveSeatUseCase
import kr.hhplus.be.server.application.ports.outbound.concert.ConcertScheduleCommandPort
import kr.hhplus.be.server.application.ports.outbound.seat.ReservationCommandPort
import kr.hhplus.be.server.application.ports.outbound.seat.SeatCommandPort
import org.springframework.stereotype.Service

/**
 * @author Doha Kim
 */
@Service
class ReserveSeatServiceImpl(
    private val reservationCommandPort: ReservationCommandPort,
    private val seatCommandPort: SeatCommandPort,
    private val concertScheduleCommandPort: ConcertScheduleCommandPort,
) : ReserveSeatUseCase {

    override fun reserve(command: ReserveSeatUseCase.ReserveSeatCommand): ReserveSeatUseCase.Output {
        // 1. 좌석 상태 TEMP_RESERVED로 변경


        // 2. 예약 정보 생성

        // 3. 이용가능한 좌석 카운트 수 감소

        // 4. 예약 정보 저장
        // TODO: 스케줄러 구현


    }
}
