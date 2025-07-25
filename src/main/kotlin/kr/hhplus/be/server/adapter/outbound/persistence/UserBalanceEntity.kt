package kr.hhplus.be.server.adapter.outbound.persistence

import jakarta.persistence.*
import kr.hhplus.be.server.domain.user.UserBalance
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
@Entity
@Table(name = "user_balance")
data class UserBalanceEntity(
    @Id
    @Column(name = "user_id")
    val userId: String,

    @Column(nullable = false)
    val balance: BigDecimal,

    @Version
    @Column(name = "version", nullable = false)
    val version: Int = 0,

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

fun UserBalance.toEntity(): UserBalanceEntity =
    UserBalanceEntity(
        userId = this.userId,
        balance = this.balance
    )

fun UserBalanceEntity.toDomain(): UserBalance =
    UserBalance(
        userId = this.userId,
        balance = this.balance,
        updatedAt = this.updatedAt!!,
    )
