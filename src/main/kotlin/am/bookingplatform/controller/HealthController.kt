package am.bookingplatform.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {

    @GetMapping("/")
    fun status(): Map<String, String> = mapOf(
        "service" to "booking-platform",
        "status" to "ok"
    )
}
