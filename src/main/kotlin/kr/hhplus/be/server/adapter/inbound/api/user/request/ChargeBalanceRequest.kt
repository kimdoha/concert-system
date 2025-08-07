package kr.hhplus.be.server.adapter.inbound.api.user.request

import java.math.BigDecimal

/**
 * @author Doha Kim
 */
data class ChargeBalanceRequest(
    val userId: String,
    val amount: BigDecimal,
) {
    fun validate() {
        require(userId.isNotBlank()) { "userId는 비어 있을 수 없습니다." }
        require(amount > BigDecimal.ZERO) { "충전 금액은 0보다 커야 합니다." }
    }
}
