package am.bookingplatform.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "listings")
data class Listing(

    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val city: String,

    @Column(nullable = false)
    val pricePerNight: Double,

    @Column(nullable = false)
    val maxGuests: Int
)
// kotlin("plugin.jpa") generates the no-arg constructor JPA needs - no manual one required.