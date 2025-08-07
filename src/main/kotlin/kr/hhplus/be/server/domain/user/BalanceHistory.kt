package kr.hhplus.be.server.domain.user

import java.math.BigDecimal

/**
 * @author Doha Kim
 */
data class BalanceHistory(
    val historyId: Int,
    val userId: String,
    val transactionType: TransactionType,
    val amount: BigDecimal,
    val balanceBeforeTransaction: BigDecimal,
    val balanceAfterTransaction: BigDecimal,
    val timestamp: Long,
) {
    enum class TransactionType {
        CHARGE, USE
    }
}
