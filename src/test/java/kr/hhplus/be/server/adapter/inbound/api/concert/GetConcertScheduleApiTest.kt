package kr.hhplus.be.server.adapter.inbound.api.concert

import com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName
import com.epages.restdocs.apispec.ResourceDocumentation.resource
import kr.hhplus.be.server.adapter.outbound.persistence.concert.ConcertEntity
import kr.hhplus.be.server.adapter.outbound.persistence.concert.JpaConcertRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import com.epages.restdocs.apispec.ResourceSnippetParameters
import kr.hhplus.be.server.adapter.outbound.persistence.concert.ConcertSeatEntity
import kr.hhplus.be.server.support.fixture.ConcertFixture
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author Doha Kim
 */
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@EntityScan("kr.hhplus.be.server.adapter.outbound.persistence")
@EnableJpaRepositories("kr.hhplus.be.server.adapter.outbound.persistence")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class GetConcertScheduleApiTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var concertRepository: JpaConcertRepository

    @BeforeEach
    fun setUp() {
        val concert = ConcertFixture.create()
        concertRepository.save(concert)
    }

    @DisplayName("콘서트 목록 조회 API (성공)")
    @Test
    fun `콘서트 목록 조회 성공`() {
        // Given
        val expectedConcertCount = 1

        // When
        val result = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/concerts")
                .accept(org.springframework.http.MediaType.APPLICATION_JSON)
        )

        // Then
        result.andExpect(status().isOk)
            .andExpect(jsonPath("$.concerts.length()").value(expectedConcertCount))
            .andDo(
                MockMvcRestDocumentation.document(
                    DOCUMENT_IDENTIFIER,
                    resource(
                        ResourceSnippetParameters.builder()
                            .description("예약 가능한 콘서트 목록 조회 API")
                            .responseFields(
                                fieldWithPath("concerts[].concertId").type(JsonFieldType.STRING).description("콘서트 ID"),
                                fieldWithPath("concerts[].title").type(JsonFieldType.STRING).description("콘서트 제목"),
                                fieldWithPath("concerts[].description").type(JsonFieldType.STRING).description("콘서트 설명"),
                                fieldWithPath("concerts[].location").type(JsonFieldType.STRING).description("콘서트 장소"),

                                fieldWithPath("concerts[].schedules").type(JsonFieldType.ARRAY).description("콘서트 일정 목록"),
                                fieldWithPath("concerts[].schedules[].scheduleId").type(JsonFieldType.NUMBER).description("콘서트 일정 식별자"),
                                fieldWithPath("concerts[].schedules[].performanceStartTime").type(JsonFieldType.STRING).description("공연 시작 시간"),
                                fieldWithPath("concerts[].schedules[].performanceEndTime").type(JsonFieldType.STRING).description("공연 종료 시간"),
                                fieldWithPath("concerts[].schedules[].performers").type(JsonFieldType.ARRAY).description("출연자 목록"),
                                fieldWithPath("concerts[].schedules[].totalSeatCnt").type(JsonFieldType.NUMBER).description("총 좌석 수"),
                                fieldWithPath("concerts[].schedules[].availableSeatCnt").type(JsonFieldType.NUMBER).description("예약 가능한 좌석 수"),
                                fieldWithPath("concerts[].schedules[].status").type(JsonFieldType.STRING).description("공연 상태 (예: OPEN, CLOSED)"),

                                fieldWithPath("concerts[].schedules[].seats").type(JsonFieldType.ARRAY).optional().description("좌석 정보 목록"),
                                fieldWithPath("concerts[].schedules[].seats[].seatId").type(JsonFieldType.NUMBER).description("좌석 식별자"),
                                fieldWithPath("concerts[].schedules[].seats[].seatNo").type(JsonFieldType.NUMBER).description("좌석 번호"),
                                fieldWithPath("concerts[].schedules[].seats[].seatType").type(JsonFieldType.STRING).description("좌석 타입 (예: VIP, STANDARD)"),
                                fieldWithPath("concerts[].schedules[].seats[].price").type(JsonFieldType.NUMBER).description("좌석 가격"),
                                fieldWithPath("concerts[].schedules[].seats[].status").type(JsonFieldType.STRING).description("좌석 상태 (예: AVAILABLE, RESERVED)")
                            )
                            .build()
                    )
                )
            )
    }

    companion object {
        private const val DOCUMENT_IDENTIFIER = "get-concerts"
    }
}
