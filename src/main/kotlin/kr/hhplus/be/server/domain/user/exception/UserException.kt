package kr.hhplus.be.server.domain.user.exception

import kr.hhplus.be.server.common.exception.BusinessException

/**
 * @author Doha Kim
 */
class UserNotFoundException : BusinessException(UserErrorCode.USER_NOT_FOUND)
class BalanceExceedMaxLimitException : BusinessException(UserErrorCode.BALANCE_EXCEED_MAX_LIMIT)
class InSufficientBalanceException : BusinessException(UserErrorCode.INSUFFICIENT_BALANCE)
