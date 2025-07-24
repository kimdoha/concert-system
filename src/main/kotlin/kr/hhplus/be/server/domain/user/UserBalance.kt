package kr.hhplus.be.server.domain.user

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
        require(newBalance <= MAX_BALANCE) { "최대 충전 한도를 초과했습니다." }
        return copy(balance = newBalance)
    }

    fun use(amount: BigDecimal): UserBalance {
        require(balance >= amount) {
            "잔액이 부족합니다. 현재 잔액: ${balance.toPlainString()}원, 사용하려는 금액: ${amount.toPlainString()}원"
        }

        return this.copy(balance = balance - amount)
    }

    companion object {
        private val MAX_BALANCE = BigDecimal("1000000.00")
    }
}
