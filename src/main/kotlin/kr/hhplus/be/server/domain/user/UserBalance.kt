package kr.hhplus.be.server.domain.user

import kr.hhplus.be.server.domain.user.exception.BalanceExceedMaxLimitException
import kr.hhplus.be.server.domain.user.exception.InSufficientBalanceException
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
data class UserBalance(
    val userId: String,
    val balance: BigDecimal,
    val updatedAt: LocalDateTime,
) {
    fun charge(amount: BigDecimal): UserBalance {
        val newBalance = balance + amount
        require(newBalance <= MAX_BALANCE) { throw BalanceExceedMaxLimitException() }
        return copy(balance = newBalance, updatedAt = LocalDateTime.now())
    }

    fun use(amount: BigDecimal): UserBalance {
        require(balance >= amount) { throw InSufficientBalanceException() }

        return this.copy(balance = balance - amount, updatedAt = LocalDateTime.now())
    }

    companion object {
        private val MAX_BALANCE = BigDecimal("1000000.00")
    }
}
