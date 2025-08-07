package kr.hhplus.be.server.domain.concert.exception

import kr.hhplus.be.server.common.exception.BusinessException

/**
 * @author Doha Kim
 */
class ConcertNotFoundException : BusinessException(ConcertErrorCode.CONCERT_NOT_FOUND)
class ScheduleNotFoundException : BusinessException(ConcertErrorCode.SCHEDULE_NOT_FOUND)
class ScheduleNotAvailableException : BusinessException(ConcertErrorCode.SCHEDULE_NOT_AVAILABLE)
class SeatNotFoundException : BusinessException(ConcertErrorCode.SEAT_NOT_FOUND)
class ReservationTargetNotAvailableException : BusinessException(ConcertErrorCode.RESERVATION_TARGET_NOT_AVAILABLE)
class ConcertSeatAlreadyReservedException : BusinessException(ConcertErrorCode.RESERVATION_ALREADY_EXISTS)
