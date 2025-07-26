package kr.hhplus.be.server.adapter.outbound.persistence.user

import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Doha Kim
 */
interface JpaUserBalanceRepository : JpaRepository<UserBalanceEntity, String>
