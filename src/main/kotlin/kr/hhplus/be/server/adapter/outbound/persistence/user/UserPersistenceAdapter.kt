package kr.hhplus.be.server.adapter.outbound.persistence.user

import kr.hhplus.be.server.application.ports.outbound.user.UserBalanceCommandPort
import kr.hhplus.be.server.application.ports.outbound.user.UserBalanceQueryPort
import kr.hhplus.be.server.domain.user.UserBalance
import org.springframework.stereotype.Repository

/**
 * @author Doha Kim
 */
@Repository
class UserPersistenceAdapter(
    private val userRepository: JpaUserRepository,
) : UserBalanceQueryPort, UserBalanceCommandPort {
    override fun getUserBalanceById(userId: String): UserBalance? {
        return userRepository.findById(userId)
            .map { it.toDomain() }
            .orElse(null)
    }

    override fun save(userBalance: UserBalance): UserBalance {
        return userRepository.save(userBalance.toEntity()).toDomain()
    }
}
