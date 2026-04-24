package com.example.sportrental.listing

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ListingService(
    private val listingRepository: ListingRepository
) {

    @Transactional(readOnly = true)
    fun findAll(): List<Listing> = listingRepository.findAll().sortedByDescending { it.createdAt }

    @Transactional(readOnly = true)
    fun findById(id: Long): Listing = listingRepository.findById(id)
        .orElseThrow { NoSuchElementException("Объявление с id=$id не найдено") }

    @Transactional
    fun create(request: ListingRequest): Listing = listingRepository.save(
        Listing(
            title = request.title.trim(),
            category = request.category.trim(),
            ownerName = request.ownerName.trim(),
            city = request.city.trim(),
            pricePerDay = request.pricePerDay,
            description = request.description.trim(),
            status = request.status
        )
    )

    @Transactional
    fun update(id: Long, request: ListingRequest): Listing {
        val listing = findById(id)
        listing.title = request.title.trim()
        listing.category = request.category.trim()
        listing.ownerName = request.ownerName.trim()
        listing.city = request.city.trim()
        listing.pricePerDay = request.pricePerDay
        listing.description = request.description.trim()
        listing.status = request.status
        return listingRepository.save(listing)
    }

    @Transactional
    fun delete(id: Long) {
        val listing = findById(id)
        listingRepository.delete(listing)
    }

    fun toResponse(listing: Listing): ListingResponse = ListingResponse(
        id = requireNotNull(listing.id),
        title = listing.title,
        category = listing.category,
        ownerName = listing.ownerName,
        city = listing.city,
        pricePerDay = listing.pricePerDay,
        description = listing.description,
        status = listing.status,
        createdAt = listing.createdAt
    )

    fun toForm(listing: Listing): ListingForm = ListingForm(
        id = listing.id,
        title = listing.title,
        category = listing.category,
        ownerName = listing.ownerName,
        city = listing.city,
        pricePerDay = listing.pricePerDay,
        description = listing.description,
        status = listing.status
    )

    fun toRequest(form: ListingForm): ListingRequest = ListingRequest(
        title = form.title,
        category = form.category,
        ownerName = form.ownerName,
        city = form.city,
        pricePerDay = requireNotNull(form.pricePerDay),
        description = form.description,
        status = requireNotNull(form.status)
    )
}
