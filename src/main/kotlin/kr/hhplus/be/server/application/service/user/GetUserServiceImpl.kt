package kr.hhplus.be.server.application.service.user

import kr.hhplus.be.server.application.ports.inbound.user.GetUserUseCase
import kr.hhplus.be.server.application.ports.outbound.user.UserQueryPort
import kr.hhplus.be.server.domain.user.exception.UserNotFoundException
import org.springframework.stereotype.Service

/**
 * @author Doha Kim
 */
@Service
class GetUserServiceImpl(
    private val userQueryPort: UserQueryPort,
) : GetUserUseCase {

    override fun getUser(userId: String): GetUserUseCase.Output {
        val user = userQueryPort.getUserById(userId)
            ?: throw UserNotFoundException()

        return GetUserUseCase.Output(
            userId = user.userId,
            name = user.userName,
        )
    }
}
