package kr.hhplus.be.server.adapter.inbound.api.concert

import kr.hhplus.be.server.adapter.inbound.api.concert.response.GetAvailableConcertSchedulesResponse
import kr.hhplus.be.server.application.ports.inbound.concert.GetAvailableConcertSchedulesUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Doha Kim
 */
@RequestMapping("/api/v1/concerts")
@RestController
class GetConcertScheduleController(
    private val getAvailableConcertSchedulesUseCase: GetAvailableConcertSchedulesUseCase,
) {
    @GetMapping("")
    fun getAvailableConcerts(
        // @RequestParam(defaultValue = "true") includeSeats: Boolean, // TODO 추후 확장
    ): GetAvailableConcertSchedulesResponse {
        val response = getAvailableConcertSchedulesUseCase.getConcerts()
        return GetAvailableConcertSchedulesResponse(
            concerts = response.concerts.map {
                GetAvailableConcertSchedulesResponse.from(it)
            }
        )
    }
}
