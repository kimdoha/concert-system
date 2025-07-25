package kr.hhplus.be.server.adapter.outbound.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import kr.hhplus.be.server.domain.user.UserBalance
import java.math.BigDecimal

/**
 * @author Doha Kim
 */
@Entity
@Table(name = "user_balance")
data class UserBalanceEntity(
    @Id
    val userId: String,

    @Column(nullable = false)
    val balance: BigDecimal,
)

fun UserBalance.toEntity(): UserBalanceEntity =
    UserBalanceEntity(
        userId = this.userId,
        balance = this.balance
    )

fun UserBalanceEntity.toDomain(): UserBalance =
    UserBalance(
        userId = this.userId,
        balance = this.balance
    )
