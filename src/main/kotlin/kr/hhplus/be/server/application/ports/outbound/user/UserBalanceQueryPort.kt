package kr.hhplus.be.server.application.ports.outbound.user

import kr.hhplus.be.server.domain.user.UserBalance

/**
 * @author Doha Kim
 */
interface UserBalanceQueryPort {
    fun getUserBalanceById(userId: String): UserBalance?
}
