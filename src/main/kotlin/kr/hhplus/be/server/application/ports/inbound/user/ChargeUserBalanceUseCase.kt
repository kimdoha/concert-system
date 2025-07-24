package kr.hhplus.be.server.application.ports.inbound.user

import kr.hhplus.be.server.application.service.user.ChargeUserBalanceService
import java.math.BigDecimal

/**
 * @author Doha Kim
 */
interface ChargeUserBalanceUseCase {
    fun chargeBalance(userId: String, amount: BigDecimal): ChargeUserBalanceService.Output
}
