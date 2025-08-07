package kr.hhplus.be.server.adapter.outbound.persistence.user

import kr.hhplus.be.server.application.ports.outbound.user.UserBalanceCommandPort
import kr.hhplus.be.server.application.ports.outbound.user.UserBalanceQueryPort
import kr.hhplus.be.server.application.ports.outbound.user.UserQueryPort
import kr.hhplus.be.server.domain.user.User
import kr.hhplus.be.server.domain.user.UserBalance
import org.springframework.stereotype.Repository

/**
 * @author Doha Kim
 */
@Repository
class UserPersistenceAdapter(
    private val userBalanceRepository: JpaUserBalanceRepository,
    private val userRepository: JpaUserRepository,
) : UserBalanceQueryPort, UserBalanceCommandPort, UserQueryPort {

    override fun getUserBalanceById(userId: String): UserBalance? {
        return userBalanceRepository.findById(userId)
            .map { it.toDomain() }
            .orElse(null)
    }

    override fun save(userBalance: UserBalance): UserBalance {
        return userBalanceRepository.save(userBalance.toEntity()).toDomain()
    }

    override fun getUserById(userId: String): User? {
        return userRepository.findById(userId)
            .map { it.toDomain() }
            .orElse(null)
    }
}
