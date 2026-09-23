package am.bookingplatform.repository

import am.bookingplatform.model.Listing
import org.springframework.stereotype.Repository
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Repository
class ListingRepository {
    private val listings = ConcurrentHashMap<UUID, Listing>()

    fun findAll(): List<Listing> = listings.values.toList()

    fun findById(id: UUID): Listing? = listings[id]

    fun save(listing: Listing): Listing {
        listings[listing.id] = listing
        return listing
    }
}