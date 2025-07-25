package kr.hhplus.be.server.adapter.outbound.persistence

import kr.hhplus.be.server.application.ports.outbound.user.UserBalanceCommandPort
import kr.hhplus.be.server.application.ports.outbound.user.UserBalanceQueryPort
import kr.hhplus.be.server.domain.user.UserBalance
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

/**
 * @author Doha Kim
 */
@Repository
class UserPersistenceAdapter(
    private val userRepository: JpaUserRepository,
) : UserBalanceQueryPort, UserBalanceCommandPort {
    override fun getUserBalanceById(userId: String): UserBalance? {
        return userRepository.findById(userId).getOrNull()?.toDomain()
    }

    override fun save(userBalance: UserBalance): UserBalance {
        return userRepository.save(userBalance.toEntity()).toDomain()
    }
}
