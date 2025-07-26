package kr.hhplus.be.server.adapter.outbound.persistence.concert

import jakarta.persistence.*
import kr.hhplus.be.server.domain.concert.Concert
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
@Entity
@Table(name = "concert")
data class ConcertEntity(
    @Id
    @Column(name = "concert_id")
    val concertId: String,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val description: String,

    @Column(nullable = false)
    val location: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: Concert.ConcertStatus,

    @OneToMany(
        mappedBy = "concertSchedule",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        orphanRemoval = true,
    )
    var schedules: List<ConcertScheduleEntity> = emptyList(),

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

    fun setSchedules(schedules: List<ConcertScheduleEntity>) {
        schedules.forEach { it.concert = this }
        this.schedules = schedules
    }
}

fun ConcertEntity.toDomain(): Concert =
    Concert(
        concertId = this.concertId,
        title = this.title,
        description = this.description,
        location = this.location,
        schedules = this.schedules.map { it.toDomain() },
        status = this.status,
    )
