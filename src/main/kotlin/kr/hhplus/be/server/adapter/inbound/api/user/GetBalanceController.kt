package kr.hhplus.be.server.adapter.inbound.api.user

import kr.hhplus.be.server.adapter.inbound.api.user.response.UserBalanceResponse
import kr.hhplus.be.server.application.ports.inbound.user.GetUserBalanceUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author Doha Kim
 */
@RequestMapping("/api/v1/user")
@RestController
class GetBalanceController(
    private val getBalanceUseCase: GetUserBalanceUseCase,
) {
    @GetMapping("/balance")
    fun getBalance(
        @RequestParam userId: String,
    ): UserBalanceResponse {
        val response = getBalanceUseCase.getUserBalance(userId)
        return UserBalanceResponse(response.userId, response.balance, response.lastUpdatedAt)
    }
}
