package kr.hhplus.be.server.application.ports.inbound.user

import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
interface ChargeUserBalanceUseCase {
    fun chargeBalance(userId: String, amount: BigDecimal): Output

    data class Output(
        val userId: String,
        val balance: BigDecimal,
        val updatedAt: LocalDateTime,
    )
}
