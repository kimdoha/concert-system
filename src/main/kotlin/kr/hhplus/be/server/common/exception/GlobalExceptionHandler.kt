package kr.hhplus.be.server.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * @author Doha Kim
 */
@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    /**
     * 비즈니스 규칙 예외 처리
     * 비즈니스 로직에서 발생하는 모든 예외를 처리합니다.
     */
    @ExceptionHandler(BusinessException::class)
    fun handlerBusinessException(ex: BusinessException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(ex.errorCode.httpStatus)
            .body(ErrorResponse.from(ex.errorCode))
    }

    /**
     * 요청 데이터 유효성 검증 실패 예외 처리
     * Bean Validation 등에서 발생하는 유효성 검증 실패를 처리합니다.
     */ // <- 오류 미해결
    //    @ExceptionHandler(MethodArgumentNotValidException::class)
    //    override fun handleMethodArgumentNotValid(
    //        ex: MethodArgumentNotValidException,
    //        headers: HttpHeaders,
    //        status: HttpStatusCode,
    //        request: WebRequest,
    //    ): ResponseEntity<Any>? {
    //        val errors = ex.bindingResult.fieldErrors.map {
    //            it.defaultMessage ?: "필드 ${it.field} 값이 유효하지 않습니다."
    //        }
    //        return ResponseEntity
    //            .status(status)
    //            .body(ErrorResponse.of(HttpErrorCode.INVALID_REQUEST_PARAM, errors))
    //    }

    /**
     * 처리되지 않은 시스템 예외 처리
     * 시스템 내부에서 발생한 예기치 않은 오류를 처리합니다.
     */
    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(getInternalServerErrorResponse())
    }

    private fun getInternalServerErrorResponse() = ErrorResponse.from(HttpErrorCode.INTERNAL_SERVER_ERROR)
}
