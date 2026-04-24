package com.example.sportrental.listing

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.PrePersist
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "listings")
class Listing(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 120)
    var title: String = "",

    @Column(nullable = false, length = 60)
    var category: String = "",

    @Column(nullable = false, length = 80)
    var ownerName: String = "",

    @Column(nullable = false, length = 80)
    var city: String = "",

    @Column(nullable = false, precision = 10, scale = 2)
    var pricePerDay: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false, length = 500)
    var description: String = "",

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var status: ListingStatus = ListingStatus.AVAILABLE,

    @Column(nullable = false)
    var createdAt: LocalDateTime? = null
) {
    @PrePersist
    fun prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now()
        }
    }
}
