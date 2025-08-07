package kr.hhplus.be.server.application.ports.outbound.seat

import kr.hhplus.be.server.domain.reservation.Reservation

/**
 * @author Doha Kim
 */
interface ReservationCommandPort {
    fun save(reservation: Reservation): Reservation
}
