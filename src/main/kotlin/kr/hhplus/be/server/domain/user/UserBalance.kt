package kr.hhplus.be.server.domain.user

import kr.hhplus.be.server.domain.user.exception.BalanceExceedMaxLimitException
import kr.hhplus.be.server.domain.user.exception.InSufficientBalanceException
import java.math.BigDecimal

/**
 * @author Doha Kim
 */
data class UserBalance(
    val userId: String,
    val balance: BigDecimal,
) {
    fun charge(amount: BigDecimal): UserBalance {
        val newBalance = balance + amount
        require(newBalance <= MAX_BALANCE) { throw BalanceExceedMaxLimitException() }
        return copy(balance = newBalance)
    }

    fun use(amount: BigDecimal): UserBalance {
        require(balance >= amount) { throw InSufficientBalanceException() }

        return this.copy(balance = balance - amount)
    }

    companion object {
        private val MAX_BALANCE = BigDecimal("1000000.00")
    }
}
