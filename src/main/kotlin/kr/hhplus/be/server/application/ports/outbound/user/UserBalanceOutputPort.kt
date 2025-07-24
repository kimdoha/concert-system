package kr.hhplus.be.server.application.ports.outbound.user

import kr.hhplus.be.server.domain.user.UserBalance

/**
 * @author Doha Kim
 */
interface UserBalanceOutputPort {
    fun getUserBalanceById(userId: String): UserBalance?
    fun save(userBalance: UserBalance): UserBalance
}
