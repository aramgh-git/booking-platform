package am.bookingplatform.repository

import am.bookingplatform.model.Listing
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ListingRepository : JpaRepository<Listing, UUID>