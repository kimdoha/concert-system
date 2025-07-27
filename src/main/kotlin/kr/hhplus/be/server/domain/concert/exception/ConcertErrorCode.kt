package kr.hhplus.be.server.domain.concert.exception

import kr.hhplus.be.server.common.exception.ErrorCode
import org.springframework.http.HttpStatus

/**
 * @author Doha Kim
 */
enum class ConcertErrorCode(
    override val code: String,
    override val message: String,
    override val httpStatus: HttpStatus,
) : ErrorCode {
    CONCERT_NOT_FOUND("CREC0001", "공연을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    SCHEDULE_NOT_FOUND("CREC0002", "공연 일정을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    SEAT_NOT_FOUND("CREC0003", "좌석을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    RESERVATION_TARGET_NOT_AVAILABLE("CREC0004", "예약 대상이 아닙니다.", HttpStatus.BAD_REQUEST),
    RESERVATION_ALREADY_EXISTS("CREC0005", "이미 예약된 좌석입니다.", HttpStatus.BAD_REQUEST),
}
