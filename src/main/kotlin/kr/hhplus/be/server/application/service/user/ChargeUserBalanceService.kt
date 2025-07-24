package kr.hhplus.be.server.application.service.user

import kr.hhplus.be.server.application.ports.inbound.user.ChargeUserBalanceUseCase
import kr.hhplus.be.server.application.ports.outbound.user.UserBalanceOutputPort
import org.springframework.stereotype.Service
import java.math.BigDecimal

/**
 * @author Doha Kim
 */
@Service
class ChargeUserBalanceService(
    private val userBalanceOutputPort: UserBalanceOutputPort,
): ChargeUserBalanceUseCase {

    data class Input(
        val userId: String,
        val amount: BigDecimal,
    ) {
        init {
            require(userId.isNotBlank()) { "userId는 비어 있을 수 없습니다." }
            require(amount > BigDecimal.ZERO) { "충전 금액은 0보다 커야 합니다." }
        }
    }

    data class Output(
        val userId: String,
        val balance: BigDecimal,
    )

    override fun chargeBalance(userId: String, amount: BigDecimal): Output {
        val userBalance = userBalanceOutputPort.getUserBalanceById(userId)
            ?: throw IllegalArgumentException("사용자를 찾을 수 없습니다. userId: $userId")

        val updatedBalance = userBalance.charge(amount)
        userBalanceOutputPort.save(updatedBalance)

        return Output(
            userId = updatedBalance.userId,
            balance = updatedBalance.balance,
        )
    }
}
