package com.example.sportrental.listing

import org.springframework.data.jpa.repository.JpaRepository

interface ListingRepository : JpaRepository<Listing, Long>
