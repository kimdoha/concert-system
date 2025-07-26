package kr.hhplus.be.server.application.facade

import kr.hhplus.be.server.application.ports.inbound.concert.GetAvailableConcertSchedulesUseCase
import kr.hhplus.be.server.application.ports.inbound.payment.ProcessPaymentUseCase
import kr.hhplus.be.server.application.ports.inbound.payment.ReservationAndPaymentUseCase
import kr.hhplus.be.server.application.ports.inbound.payment.ReserveSeatUseCase
import kr.hhplus.be.server.application.ports.inbound.user.GetUserBalanceUseCase
import kr.hhplus.be.server.application.ports.inbound.user.GetUserUseCase

/**
 * @author Doha Kim
 */
class ReservationAndPaymentFacade(
    private val getUserUseCase: GetUserUseCase,

    private val reserveSeatUseCase: ReserveSeatUseCase,
    private val processPaymentUseCase: ProcessPaymentUseCase,
) : ReservationAndPaymentUseCase {
    override fun reserveAndPay(command: ReservationAndPaymentUseCase.ReservationAndPaymentCommand): ReservationAndPaymentUseCase.ReservationAndPaymentResult {
        // 1. 유저 유효성 확인
        val user = getUserUseCase.getUser(command.userId)

        // 2. 콘서트/스케줄/좌석 유효성 확인
        val concert = getAvailableConcertScheduleUseCase.

        // 3. 좌석 예약 요청

        // 4. 유저 잔액 확인

        // 5. 결제 처리

        // 6. 결과 반환
    }
}
