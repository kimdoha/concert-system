package kr.hhplus.be.server.adapter.inbound.api.user

import com.epages.restdocs.apispec.ResourceDocumentation.resource
import com.epages.restdocs.apispec.ResourceSnippetParameters
import com.fasterxml.jackson.databind.ObjectMapper
import kr.hhplus.be.server.adapter.inbound.api.user.request.ChargeBalanceRequest
import kr.hhplus.be.server.adapter.outbound.persistence.user.JpaUserBalanceRepository
import kr.hhplus.be.server.adapter.outbound.persistence.user.UserBalanceEntity
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal

/**
 * @author Doha Kim
 */
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@EntityScan("kr.hhplus.be.server.adapter.outbound.persistence")
@EnableJpaRepositories("kr.hhplus.be.server.adapter.outbound.persistence")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class ChargeUserBalanceApiTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var userBalanceRepository: JpaUserBalanceRepository

    @BeforeEach
    fun setup() {
        val userBalance = UserBalanceEntity(
            userId = TEST_USER_ID,
            balance = BigDecimal.ZERO
        )
        userBalanceRepository.save(userBalance)
    }

    @DisplayName("사용자 잔액 충전 API (성공)")
    @Test
    fun `사용자 잔액 충전 성공`() {
        // Given
        val request = ChargeBalanceRequest(userId = TEST_USER_ID, amount = BigDecimal("1000.0"))

        // When & Then
        mockMvc.perform(
            post("/api/v1/user/balance/charge")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.userId").value(request.userId))
            .andExpect(jsonPath("$.balance").value(request.amount))
            .andExpect(jsonPath("$.lastUpdatedAt").isNotEmpty)
            .andDo {
                MockMvcRestDocumentation.document(
                    DOCUMENT_IDENTIFIER,
                    resource(
                        ResourceSnippetParameters.builder()
                            .description("사용자 잔액 충전 API")
                            .requestFields(
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("사용자 식별자"),
                                fieldWithPath("amount").type(JsonFieldType.NUMBER).description("충전할 잔액 금액")
                            )
                            .responseFields(
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("사용자 식별자"),
                                fieldWithPath("balance").type(JsonFieldType.NUMBER).description("충전된 금액"),
                                fieldWithPath("lastUpdatedAt").type(JsonFieldType.STRING).description("마지막 업데이트 시간"),
                            )
                            .build()
                    )
                )
            }
    }

    companion object {
        private const val DOCUMENT_IDENTIFIER = "charge-user-balance"
        private const val TEST_USER_ID = "test-user-1"
    }
}
