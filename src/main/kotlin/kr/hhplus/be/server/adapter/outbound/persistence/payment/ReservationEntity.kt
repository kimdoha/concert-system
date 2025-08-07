package kr.hhplus.be.server.adapter.outbound.persistence.payment

import jakarta.persistence.*
import kr.hhplus.be.server.domain.reservation.Reservation
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
@Entity
@Table(name = "reservation")
class ReservationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "reservation_id", nullable = false, unique = true)
    val reservationId: String,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "concert_id", nullable = false)
    val concertId: Long,

    @Column(name = "schedule_id", nullable = false)
    val scheduleId: Long,

    @Column(name = "seat_id", nullable = false)
    val seatId: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: Reservation.ReservationStatus,

    @Column(name = "reservation_ymdt", updatable = false)
    var reservationYmdt: LocalDateTime? = null,

    @Column(name = "reservation_until")
    var reservationUntil: LocalDateTime? = null,
) {
    @PrePersist
    fun prePersist() {
        val now = LocalDateTime.now()
        reservationYmdt = now
        reservationUntil = now.plusMinutes(RESERVATION_UNTIL_MINUTES)
    }

    companion object {
        private val RESERVATION_UNTIL_MINUTES = 5L
    }
}
