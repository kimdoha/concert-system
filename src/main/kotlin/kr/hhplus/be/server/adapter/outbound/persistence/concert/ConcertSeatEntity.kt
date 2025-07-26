package kr.hhplus.be.server.adapter.outbound.persistence.concert

import jakarta.persistence.*
import kr.hhplus.be.server.domain.concert.ConcertSeat
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
@Entity
@Table(name = "concert_seat")
class ConcertSeatEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_schedule_id", nullable = false)
    var concertSchedule: ConcertScheduleEntity,

    @Column(name = "seat_no", nullable = false)
    val seatNo: Int,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: ConcertSeat.SeatType,

    val price: BigDecimal,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: ConcertSeat.SeatStatus,

    @Column(name = "created_ymdt", updatable = false)
    var createdAt: LocalDateTime? = null,

    @Column(name = "updated_ymdt")
    var updatedAt: LocalDateTime? = null,
) {
    @PrePersist
    fun prePersist() {
        val now = LocalDateTime.now()
        createdAt = now
        updatedAt = now
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = LocalDateTime.now()
    }
}

fun ConcertSeatEntity.toDomain(): ConcertSeat =
    ConcertSeat(
        seatId = this.id,
        seatNo = this.seatNo,
        price = this.price,
        status = this.status,
        seatType = this.type,
    )
