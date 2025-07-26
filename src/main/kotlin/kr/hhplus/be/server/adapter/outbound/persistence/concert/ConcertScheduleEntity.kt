package kr.hhplus.be.server.adapter.outbound.persistence.concert

import jakarta.persistence.*
import kr.hhplus.be.server.domain.concert.ConcertSchedule
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
@Entity
@Table(name = "concert_schedule")
data class ConcertScheduleEntity(
    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id", nullable = false)
    var concert: ConcertEntity,

    @Column(name = "performance_start_ymdt")
    val performanceStartTime: LocalDateTime,

    @Column(name = "performance_end_ymdt")
    val performanceEndTime: LocalDateTime,

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "concert_schedule_performer",
        joinColumns = [JoinColumn(name = "schedule_id")]
    )
    val performers: List<String>,

    @Column(name = "total_seat_cnt")
    val totalSeatCnt: Int,

    @Column(name = "available_seat_cnt")
    val availableSeatCnt: Int,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: ConcertSchedule.ScheduleStatus,

    @OneToMany(
        mappedBy = "concertSchedule",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        orphanRemoval = true,
    )
    var seats: List<ConcertSeatEntity>,

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

    fun setSeats(seats: List<ConcertSeatEntity>) {
        seats.forEach { it.concertSchedule = this }
        this.seats = seats
    }
}

fun ConcertScheduleEntity.toDomain(): ConcertSchedule =
    ConcertSchedule(
        performanceStartTime = this.performanceStartTime,
        performanceEndTime = this.performanceEndTime,
        performers = this.performers,
        totalSeatCnt = this.totalSeatCnt,
        availableSeatCnt = this.availableSeatCnt,
        status = this.status,
        seats = this.seats.map { it.toDomain() },
    )
