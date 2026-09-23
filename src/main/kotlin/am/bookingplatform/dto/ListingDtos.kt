package am.bookingplatform.dto

import am.bookingplatform.model.Listing
import java.util.UUID

data class CreateListingRequest(
    val title: String,
    val city: String,
    val pricePerNight: Double,
    val maxGuests: Int
)

data class ListingResponse(
    val id: UUID,
    val title: String,
    val city: String,
    val pricePerNight: Double,
    val maxGuests: Int
) {
    companion object {
        fun from(listing: Listing) = ListingResponse(
            id = listing.id,
            title = listing.title,
            city = listing.city,
            pricePerNight = listing.pricePerNight,
            maxGuests = listing.maxGuests
        )
    }
}