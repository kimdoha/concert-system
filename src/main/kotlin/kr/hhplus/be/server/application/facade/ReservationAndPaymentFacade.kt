package kr.hhplus.be.server.application.facade

import jakarta.transaction.Transactional
import kr.hhplus.be.server.application.ports.inbound.concert.CheckAvailableReservationTargetUseCase
import kr.hhplus.be.server.application.ports.inbound.payment.ProcessPaymentUseCase
import kr.hhplus.be.server.application.ports.inbound.payment.ReservationAndPaymentUseCase
import kr.hhplus.be.server.application.ports.inbound.payment.ReserveSeatUseCase
import kr.hhplus.be.server.application.ports.inbound.user.GetUserUseCase
import org.springframework.stereotype.Service

/**
 * @author Doha Kim
 */
@Service
class ReservationAndPaymentFacade(
    private val getUserUseCase: GetUserUseCase,
    private val checkAvailableReservationTargetUseCase: CheckAvailableReservationTargetUseCase,
    private val reserveSeatUseCase: ReserveSeatUseCase,
    private val processPaymentUseCase: ProcessPaymentUseCase,
) : ReservationAndPaymentUseCase {

    @Transactional
    override fun reserveAndPay(command: ReservationAndPaymentUseCase.ReservationAndPaymentCommand): ReservationAndPaymentUseCase.ReservationAndPaymentResult {
        // 1. 유저 유효성 확인
        val user = getUserUseCase.getUser(command.userId)

        // 2. 콘서트/스케줄/좌석 유효성 확인
        val (concert, schedule, seat) = checkAvailableReservationTargetUseCase.validateAndGetTargets(
            command.toValidateReservationTargetCommand()
        )

        // 3. 좌석 예약 요청
        val reservationSeat = reserveSeatUseCase.reserve(
            ReserveSeatUseCase.ReserveSeatCommand(
                userId = user.id,
                concertId = concert.id,
                scheduleId = schedule.id,
                seatId = seat.id,
            )
        )

        // 4. 결제 처리 (유저 잔액 확인)
        val payment = processPaymentUseCase.pay(
            ProcessPaymentUseCase.PayCommand(
                userId = user.userId,
                reservationId = reservationSeat.reservationId,
            )
        )

        // 5. 결과 반환
        return ReservationAndPaymentUseCase.ReservationAndPaymentResult(
            reservationId = reservationSeat.reservationId,
            paymentId = payment.paymentId,
            totalAmount = payment.totalAmount,
            reservedYmdt = reservationSeat.reservedYmdt,
            reservedUntil = reservationSeat.reservedUntil,
            paidYmdt = payment.paidYmdt,
        )
    }
}
