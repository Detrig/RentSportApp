package com.example.sportrental

import com.example.sportrental.listing.ListingRequest
import com.example.sportrental.listing.ListingStatus
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal

@SpringBootTest
@AutoConfigureMockMvc
class ListingRestControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun `should create and return listing`() {
        val request = ListingRequest(
            title = "Сноуборд Burton",
            category = "Snowboard",
            ownerName = "Алексей",
            city = "Казань",
            pricePerDay = BigDecimal("1800.00"),
            description = "Хорошее состояние, крепления в комплекте",
            status = ListingStatus.AVAILABLE
        )

        mockMvc.perform(
            post("/api/listings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.title").value("Сноуборд Burton"))
            .andExpect(jsonPath("$.ownerName").value("Алексей"))
            .andExpect(jsonPath("$.status").value("AVAILABLE"))

        val response = mockMvc.perform(get("/api/listings"))
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsString

        assertTrue(response.contains("Сноуборд Burton"))
    }

    @Test
    fun `should delete listing`() {
        val request = ListingRequest(
            title = "Ракетка Head",
            category = "Tennis",
            ownerName = "Мария",
            city = "Сочи",
            pricePerDay = BigDecimal("500.00"),
            description = "Подойдет для начинающих",
            status = ListingStatus.AVAILABLE
        )

        val created = mockMvc.perform(
            post("/api/listings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isCreated)
            .andReturn()
            .response
            .contentAsString

        val createdId = objectMapper.readTree(created).get("id").asLong()

        mockMvc.perform(delete("/api/listings/$createdId"))
            .andExpect(status().isNoContent)
    }
}
