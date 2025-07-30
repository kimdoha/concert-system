package kr.hhplus.be.server.application.ports.inbound.user

/**
 * @author Doha Kim
 */
interface GetUserUseCase {
    fun getUser(userId: String): Output

    data class Output(
        val id: Long,
        val userId: String,
        val name: String,
    )
}
