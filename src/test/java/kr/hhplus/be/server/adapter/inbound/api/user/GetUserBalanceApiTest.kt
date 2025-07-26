package kr.hhplus.be.server.adapter.inbound.api.user

import com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName
import com.epages.restdocs.apispec.ResourceDocumentation.resource
import com.epages.restdocs.apispec.ResourceSnippetParameters
import kr.hhplus.be.server.adapter.outbound.persistence.user.JpaUserRepository
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import java.time.LocalDateTime
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

/**
 * @author Doha Kim
 */
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@EntityScan("kr.hhplus.be.server.adapter.outbound.persistence")
@EnableJpaRepositories("kr.hhplus.be.server.adapter.outbound.persistence")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class GetUserBalanceApiTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var userBalanceRepository: JpaUserRepository

    @BeforeEach
    fun setup() {
        val userBalance = UserBalanceEntity(
            userId = TEST_USER_ID,
            balance = BigDecimal("50000.00"),
            version = 0,
            createdAt = LocalDateTime.now().minusDays(1),
            updatedAt = LocalDateTime.now()
        )
        userBalanceRepository.save(userBalance)
    }

    @DisplayName("사용자 잔액 조회 API (성공)")
    @Test
    fun `사용자 잔액 조회 성공`() {
        // Given
        val userId = TEST_USER_ID

        // When & Then
        mockMvc.perform(
            get("/api/v1/users/{userId}/balance", userId)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.userId").value(userId))
            .andExpect(jsonPath("$.balance").isNotEmpty)
            .andExpect(jsonPath("$.lastUpdatedAt").isNotEmpty)
            .andDo(
                MockMvcRestDocumentation.document(
                    DOCUMENT_IDENTIFIER,
                    resource(
                        ResourceSnippetParameters.builder()
                            .description("사용자 잔액 조회 API")
                            .pathParameters(
                                parameterWithName("userId").description("사용자 식별자")
                            )
                            .responseFields(
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("사용자 식별자"),
                                fieldWithPath("balance").type(JsonFieldType.NUMBER).description("충전된 금액"),
                                fieldWithPath("lastUpdatedAt").type(JsonFieldType.STRING).description("마지막 업데이트 시간"),
                            )
                            .build()
                    )
                )
            )
    }

    companion object {
        private const val DOCUMENT_IDENTIFIER = "charge-user-balance"
        private const val TEST_USER_ID = "test-user-1"
    }
}
