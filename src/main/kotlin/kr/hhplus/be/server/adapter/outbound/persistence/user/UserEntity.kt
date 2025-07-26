package kr.hhplus.be.server.adapter.outbound.persistence.user

import jakarta.persistence.*
import kr.hhplus.be.server.domain.user.User
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
@Entity
@Table(name = "user")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "user_id", nullable = false, unique = true)
    val userId: String,

    @Column(name = "user_name", nullable = false)
    val userName: String,

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

fun User.toEntity(): UserEntity =
    UserEntity(
        userId = this.userId,
        userName = this.userName
    )

fun UserEntity.toDomain(): User =
    User(
        userId = this.userId,
        userName = this.userName,
    )
