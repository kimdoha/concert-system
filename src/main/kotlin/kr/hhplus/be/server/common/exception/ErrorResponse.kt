package kr.hhplus.be.server.common.exception

/**
 * @author Doha Kim
 */
data class ErrorResponse(
    val success: Boolean = false,
    val code: String,
    val message: String,
    val status: Int,
    val errors: List<String> = emptyList(),
) {
    companion object {
        fun from(errorCode: ErrorCode) = ErrorResponse(
            code = errorCode.code,
            message = errorCode.message,
            status = errorCode.httpStatus.value(),
        )

        fun of(errorCode: ErrorCode, errors: List<String>) = ErrorResponse(
            code = errorCode.code,
            message = errorCode.message,
            status = errorCode.httpStatus.value(),
            errors = errors,
        )
    }
}
