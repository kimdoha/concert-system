package kr.hhplus.be.server.adapter.inbound.api.user

import kr.hhplus.be.server.adapter.inbound.api.user.request.ChargeBalanceRequest
import kr.hhplus.be.server.adapter.inbound.api.user.response.UserBalanceResponse
import kr.hhplus.be.server.application.ports.inbound.user.ChargeUserBalanceUseCase
import kr.hhplus.be.server.application.service.user.ChargeUserBalanceServiceImpl
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Doha Kim
 */
@RequestMapping("/api/v1/user")
@RestController
class ChargeUserBalanceController(
    private val chargeBalanceUseCase: ChargeUserBalanceUseCase,
) {
    @PostMapping("/balance/charge")
    fun chargeBalance(
        @RequestBody body: ChargeBalanceRequest,
    ): UserBalanceResponse {
        body.validate()
        val response = chargeBalanceUseCase.chargeBalance(body.userId, body.amount)
        return UserBalanceResponse(response.userId, response.balance)
    }
}
