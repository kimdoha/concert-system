package kr.hhplus.be.server.adapter.inbound.api.concert.response

import kr.hhplus.be.server.application.ports.inbound.concert.GetAvailableConcertSchedulesUseCase
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
class GetAvailableConcertSchedulesResponse(
    val concerts: List<ConcertResponse>,
) {
    companion object {
        fun from(concert: GetAvailableConcertSchedulesUseCase.ConcertScheduleDetail): ConcertResponse {
            return ConcertResponse.from(concert)
        }
    }
}

data class ConcertResponse(
    val concertId: String,
    val title: String,
    val description: String,
    val location: String,
    val schedules: List<ConcertScheduleResponse>,
) {
    companion object {
        fun from(concert: GetAvailableConcertSchedulesUseCase.ConcertScheduleDetail): ConcertResponse {
            return ConcertResponse(
                concertId = concert.concertId,
                title = concert.title,
                description = concert.description,
                location = concert.location,
                schedules = concert.schedules.map {
                    ConcertScheduleResponse.from(it)
                }
            )
        }
    }
}

data class ConcertScheduleResponse(
    val performanceStartTime: LocalDateTime,
    val performanceEndTime: LocalDateTime,
    val performers: List<String>,
    val totalSeatCnt: Int,
    val availableSeatCnt: Int,
    val status: String,
    val seats: List<ConcertSeatResponse> = emptyList(),
) {
    companion object {
        fun from(schedule: GetAvailableConcertSchedulesUseCase.ConcertSchedule): ConcertScheduleResponse {
            return ConcertScheduleResponse(
                performanceStartTime = schedule.performanceStartTime,
                performanceEndTime = schedule.performanceEndTime,
                performers = schedule.performers,
                totalSeatCnt = schedule.totalSeatCnt,
                availableSeatCnt = schedule.availableSeatCnt,
                status = schedule.status.name,
                seats = schedule.seats.map { ConcertSeatResponse.from(it) }
            )
        }
    }
}

data class ConcertSeatResponse(
    val seatNo: Int,
    val seatType: String,
    val price: BigDecimal,
    val status: String,
) {
    companion object {
        fun from(seat: GetAvailableConcertSchedulesUseCase.ConcertSeat): ConcertSeatResponse {
            return ConcertSeatResponse(
                seatNo = seat.seatNo,
                seatType = seat.seatType.name,
                price = seat.price,
                status = seat.status.name
            )
        }
    }
}
