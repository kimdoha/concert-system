package kr.hhplus.be.server.application.service.concert

import kr.hhplus.be.server.adapter.outbound.persistence.concert.toDomain
import kr.hhplus.be.server.application.ports.inbound.concert.CheckAvailableReservationTargetUseCase
import kr.hhplus.be.server.application.ports.outbound.concert.ConcertQueryPort
import kr.hhplus.be.server.domain.concert.exception.*
import org.springframework.stereotype.Service

/**
 * @author Doha Kim
 */
@Service
class CheckAvailableReservationTargetServiceImpl(
    private val concertQueryPort: ConcertQueryPort,
) : CheckAvailableReservationTargetUseCase {

    override fun validateAndGetTargets(command: CheckAvailableReservationTargetUseCase.ValidateReservationTargetCommand): CheckAvailableReservationTargetUseCase.ValidatedReservationTarget {
        val concert =
            concertQueryPort.findConcertByConcertId(command.concertId)?.toDomain() ?: throw ConcertNotFoundException()
        if (!concert.isReservable()) throw ReservationTargetNotAvailableException()

        val schedule =
            concertQueryPort.findConcertScheduleByScheduleId(command.scheduleId)?.toDomain()
                ?: throw ScheduleNotFoundException()
        if (schedule.concertId != concert.concertId) throw ScheduleNotFoundException()

        val seat = concertQueryPort.findSeatBySeatId(command.seatId)?.toDomain() ?: throw SeatNotFoundException()
        if (seat.scheduleId != schedule.scheduleId) throw SeatNotFoundException()
        if (!seat.isReservable()) throw ConcertSeatAlreadyReservedException()

        return CheckAvailableReservationTargetUseCase.ValidatedReservationTarget(
            concert = concert,
            schedule = schedule,
            seat = seat,
        )
    }
}
