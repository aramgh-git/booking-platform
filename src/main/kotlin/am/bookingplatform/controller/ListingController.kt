package am.bookingplatform.controller

import am.bookingplatform.dto.CreateListingRequest
import am.bookingplatform.dto.ListingResponse
import am.bookingplatform.model.Listing
import am.bookingplatform.repository.ListingRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/listings")
class ListingController(private val repository: ListingRepository) {

    @GetMapping
    fun getAll(): List<ListingResponse> = repository.findAll().map { ListingResponse.from(it) }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: UUID): ResponseEntity<ListingResponse> {
        val listing = repository.findById(id).orElse(null) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(ListingResponse.from(listing))
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: CreateListingRequest): ListingResponse {
        val listing = Listing(
            id = UUID.randomUUID(),
            title = request.title,
            city = request.city,
            pricePerNight = request.pricePerNight,
            maxGuests = request.maxGuests
        )
        return ListingResponse.from(repository.save(listing))
    }

}