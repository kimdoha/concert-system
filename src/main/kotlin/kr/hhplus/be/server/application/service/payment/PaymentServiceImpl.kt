package kr.hhplus.be.server.application.service.payment

import kr.hhplus.be.server.application.ports.inbound.payment.ProcessPaymentUseCase
import kr.hhplus.be.server.application.ports.outbound.user.UserBalanceQueryPort
import org.springframework.stereotype.Service

/**
 * @author Doha Kim
 */
@Service
class PaymentServiceImpl(
    private val paymentCommandPort: PaymentCommandPort,
    private val userBalanceQueryPort: UserBalanceQueryPort,
) : ProcessPaymentUseCase {

    override fun pay(command: ProcessPaymentUseCase.PayCommand): ProcessPaymentUseCase.Output {
        // 1. 유저 잔액 확인

        // 2. 결제 정보 생성

        // 3. 결제 정보 저장
    }
}
