package kr.hhplus.be.server.adapter.outbound.persistence

import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Doha Kim
 */
interface JpaUserRepository : JpaRepository<UserBalanceEntity, String>
