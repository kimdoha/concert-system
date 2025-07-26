package kr.hhplus.be.server.application.service.user

import kr.hhplus.be.server.application.ports.inbound.user.GetUserBalanceUseCase
import kr.hhplus.be.server.application.ports.outbound.user.UserBalanceQueryPort
import kr.hhplus.be.server.domain.user.exception.UserNotFoundException
import org.springframework.stereotype.Service

/**
 * @author Doha Kim
 */
@Service
class GetUserBalanceServiceImpl(
    private val userBalanceQueryPort: UserBalanceQueryPort,
) : GetUserBalanceUseCase {

    override fun getUserBalance(userId: String): GetUserBalanceUseCase.Output {
        val userBalance = userBalanceQueryPort.getUserBalanceById(userId)
            ?: throw UserNotFoundException()

        return GetUserBalanceUseCase.Output(
            userId = userBalance.userId,
            balance = userBalance.balance,
            lastUpdatedAt = userBalance.updatedAt
        )
    }
}
