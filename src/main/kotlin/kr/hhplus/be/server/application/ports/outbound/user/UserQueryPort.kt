package kr.hhplus.be.server.application.ports.outbound.user

import kr.hhplus.be.server.domain.user.User

/**
 * @author Doha Kim
 */
interface UserQueryPort {
    fun getUserById(userId: String): User?
}
