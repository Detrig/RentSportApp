package com.example.sportrental.listing

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal

data class ListingRequest(
    @field:NotBlank(message = "Введите название инвентаря")
    @field:Size(max = 120)
    val title: String,

    @field:NotBlank(message = "Введите ")
    @field:Size(max = 60)
    val category: String,

    @field:NotBlank(message = "Введите имя владельца")
    @field:Size(max = 80)
    val ownerName: String,

    @field:NotBlank(message = "Введите город")
    @field:Size(max = 80)
    val city: String,

    @field:NotNull(message = "Введите цену за день")
    @field:DecimalMin(value = "0.0", inclusive = false, message = "Цена должна быть больше 0")
    val pricePerDay: BigDecimal,

    @field:NotBlank(message = "Введите описание")
    @field:Size(max = 500)
    val description: String,

    @field:NotNull(message = "Выберите статус")
    val status: ListingStatus
)
