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
    var id: UUID,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var city: String,

    @Column(nullable = false)
    var pricePerNight: Double,

    @Column(nullable = false)
    var maxGuests: Int
)
// kotlin("plugin.jpa") generates the no-arg constructor JPA needs - no manual one required.