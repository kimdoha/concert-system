package kr.hhplus.be.server.common.exception

/**
 * @author Doha Kim
 */
open class BusinessException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)
