package am.bookingplatform.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ListingControllerTests {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun `create then fetch listing`() {
        val body = mapOf(
            "title" to "Cozy studio near Republic Square",
            "city" to "Yerevan",
            "pricePerNight" to 45.0,
            "maxGuests" to 2
        )

        val createResult = mockMvc.perform(
            post("/listings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body))
        ).andExpect(status().isCreated)
            .andExpect(jsonPath("$.city").value("Yerevan"))
            .andReturn()

        val id = objectMapper.readTree(createResult.response.contentAsString).get("id").asText()

        mockMvc.perform(get("/listings/$id"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.title").value("Cozy studio near Republic Square"))
            .andReturn()

        mockMvc.perform(get("/listings"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].city").exists())
    }

    @Test
    fun `unknown id returns 404`() {
        mockMvc.perform(get("/listings/00000000-0000-0000-0000-000000000000"))
            .andExpect(status().isNotFound)
    }
}