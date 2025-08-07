package kr.hhplus.be.server.application.service.user

import kr.hhplus.be.server.application.ports.inbound.user.ChargeUserBalanceUseCase
import kr.hhplus.be.server.application.ports.outbound.user.UserBalanceCommandPort
import kr.hhplus.be.server.application.ports.outbound.user.UserBalanceQueryPort
import kr.hhplus.be.server.domain.user.exception.UserNotFoundException
import org.springframework.stereotype.Service
import java.math.BigDecimal

/**
 * @author Doha Kim
 */
@Service
class ChargeUserBalanceServiceImpl(
    private val userBalanceQueryPort: UserBalanceQueryPort,
    private val userBalanceCommandPort: UserBalanceCommandPort,
) : ChargeUserBalanceUseCase {

    override fun chargeBalance(userId: String, amount: BigDecimal): ChargeUserBalanceUseCase.Output {
        val userBalance = userBalanceQueryPort.getUserBalanceById(userId)
            ?: throw UserNotFoundException()

        val updatedBalance = userBalance.charge(amount)
        val savedBalance = userBalanceCommandPort.save(updatedBalance)

        return ChargeUserBalanceUseCase.Output(
            userId = savedBalance.userId,
            balance = savedBalance.balance,
            updatedAt = savedBalance.updatedAt,
        )
    }
}
