package kr.hhplus.be.server.common.exception

import org.springframework.http.HttpStatus

/**
 * @author Doha Kim
 */
interface ErrorCode {
    val code: String
    val message: String
    val httpStatus: HttpStatus
}

enum class HttpErrorCode(
    override val code: String,
    override val message: String,
    override val httpStatus: HttpStatus,
) : ErrorCode {
    // common
    INTERNAL_SERVER_ERROR("HPER0001", "시스템 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED("UNAUTHORIZED", "UNAUTHORIZED", HttpStatus.UNAUTHORIZED),
}
