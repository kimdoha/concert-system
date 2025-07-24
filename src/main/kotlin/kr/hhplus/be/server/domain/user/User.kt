package kr.hhplus.be.server.domain.user

import java.math.BigDecimal

/**
 * @author Doha Kim
 */
data class User(
    val userId: String,
    val name: String,
    val balance: BigDecimal,
)
