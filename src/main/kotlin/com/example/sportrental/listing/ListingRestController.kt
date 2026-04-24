package com.example.sportrental.listing

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/listings")
class ListingRestController(
    private val listingService: ListingService
) {

    @GetMapping
    fun findAll(): List<ListingResponse> = listingService.findAll().map(listingService::toResponse)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ListingResponse =
        listingService.toResponse(listingService.findById(id))

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody request: ListingRequest): ListingResponse =
        listingService.toResponse(listingService.create(request))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody request: ListingRequest): ListingResponse =
        listingService.toResponse(listingService.update(id, request))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        listingService.delete(id)
    }
}
