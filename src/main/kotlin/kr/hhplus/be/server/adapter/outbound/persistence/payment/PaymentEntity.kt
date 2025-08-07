package kr.hhplus.be.server.adapter.outbound.persistence.payment

import jakarta.persistence.*
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
@Entity
@Table(name = "payment")
class PaymentEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    val reservation: ReservationEntity,

    val amount: Long,

    val status: PaymentStatus,

    @Column(name = "payment_ymdt", updatable = false)
    var paymentYmdt: LocalDateTime? = null
) {
    @PrePersist
    fun prePersist() {
        val now = LocalDateTime.now()
        createdAt = now
        updatedAt = now
    }
}
