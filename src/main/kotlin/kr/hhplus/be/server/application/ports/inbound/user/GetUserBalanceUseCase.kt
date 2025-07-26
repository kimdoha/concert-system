package kr.hhplus.be.server.application.ports.inbound.user

import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
interface GetUserBalanceUseCase {
    fun getUserBalance(userId: String): Output

    data class Output(
        val userId: String,
        val balance: BigDecimal,
        val lastUpdatedAt: LocalDateTime,
    )
}
