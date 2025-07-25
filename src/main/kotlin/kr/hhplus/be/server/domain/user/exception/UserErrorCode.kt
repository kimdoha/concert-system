package kr.hhplus.be.server.domain.user.exception

import kr.hhplus.be.server.common.exception.ErrorCode
import org.springframework.http.HttpStatus

/**
 * @author Doha Kim
 */
enum class UserErrorCode(
    override val code: String,
    override val message: String,
    override val httpStatus: HttpStatus,
) : ErrorCode {
    USER_NOT_FOUND("UREC0001", "사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    BALANCE_EXCEED_MAX_LIMIT("UREC0002", "최대 충전 한도를 초과했습니다.", HttpStatus.BAD_REQUEST),
    INSUFFICIENT_BALANCE("UREC0003", "잔액이 부족합니다.", HttpStatus.BAD_REQUEST),
}
