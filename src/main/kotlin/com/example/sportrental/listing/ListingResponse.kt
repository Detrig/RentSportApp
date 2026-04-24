package com.example.sportrental.listing

import java.math.BigDecimal
import java.time.LocalDateTime

data class ListingResponse(
    val id: Long,
    val title: String,
    val category: String,
    val ownerName: String,
    val city: String,
    val pricePerDay: BigDecimal,
    val description: String,
    val status: ListingStatus,
    val createdAt: LocalDateTime?
)
