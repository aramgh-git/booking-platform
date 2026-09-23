package am.bookingplatform.model

import java.util.UUID

data class Listing(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val city: String,
    val pricePerNight: Double,
    val maxGuests: Int
)