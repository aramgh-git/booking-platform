package am.bookingplatform

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ApplicationTests {

    @Test
    fun contextLoads() {
        // Fails CI if the Spring context can't start — cheap but effective guardrail.
    }
}
