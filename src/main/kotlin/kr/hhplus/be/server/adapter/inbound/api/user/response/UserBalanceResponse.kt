package kr.hhplus.be.server.adapter.inbound.api.user.response

import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
data class UserBalanceResponse(
    val userId: String,
    val balance: BigDecimal,
    val updatedAt: LocalDateTime,
)
