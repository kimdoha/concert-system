package kr.hhplus.be.server.adapter.inbound.api.payment

import kr.hhplus.be.server.adapter.inbound.api.payment.request.ReserveAndPayRequest
import kr.hhplus.be.server.adapter.inbound.api.payment.response.ReserveAndPayResponse
import kr.hhplus.be.server.application.ports.inbound.payment.ReservationAndPaymentUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Doha Kim
 */
@RequestMapping("/api/v1/reserve-and-pay")
@RestController
class ReservationAndPayConcertController(
    private val reservationAndPaymentUseCase: ReservationAndPaymentUseCase,
) {
    @PostMapping()
    fun reserveAndPayConcert(@RequestBody request: ReserveAndPayRequest): ReserveAndPayResponse {
        val result = reservationAndPaymentUseCase.reserveAndPay(request.toCommand())
        return ReserveAndPayResponse(

        )
    }
}
