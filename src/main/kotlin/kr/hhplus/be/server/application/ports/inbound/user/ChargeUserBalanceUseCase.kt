package kr.hhplus.be.server.application.ports.inbound.user

import java.math.BigDecimal

/**
 * @author Doha Kim
 */
interface ChargeUserBalanceUseCase {
    fun chargeBalance(userId: String, amount: BigDecimal): Output

    data class Output(
        val userId: String,
        val balance: BigDecimal,
    )
}
