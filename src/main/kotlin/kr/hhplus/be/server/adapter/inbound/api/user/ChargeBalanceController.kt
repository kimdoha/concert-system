package kr.hhplus.be.server.adapter.inbound.api.user

import kr.hhplus.be.server.application.ports.inbound.user.ChargeUserBalanceUseCase
import kr.hhplus.be.server.application.service.user.ChargeUserBalanceService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Doha Kim
 */
@RequestMapping("/api/v1/user")
@RestController
class ChargeBalanceController(
    private val chargeBalanceUseCase: ChargeUserBalanceUseCase,
) {
    @PostMapping("/balance/charge")
    fun chargeBalance(
        @RequestBody body: ChargeUserBalanceService.Input,
    ): ChargeUserBalanceService.Output {
        return chargeBalanceUseCase.chargeBalance(
            userId = body.userId,
            amount = body.amount
        )
    }
}
