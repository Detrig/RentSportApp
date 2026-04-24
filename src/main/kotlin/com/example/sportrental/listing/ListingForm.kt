package com.example.sportrental.listing

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal

data class ListingForm(
    var id: Long? = null,

    @field:NotBlank(message = " название инвентаря")
    @field:Size(max = 120)
    var title: String = "",

    @field:NotBlank(message = "Введите категорию")
    @field:Size(max = 60)
    var category: String = "",

    @field:NotBlank(message = "Введите имя владельца")
    @field:Size(max = 80)
    var ownerName: String = "",

    @field:NotBlank(message = "Введите город")
    @field:Size(max = 80)
    var city: String = "",

    @field:NotNull(message = "Введите цену за день")
    @field:DecimalMin(value = "0.0", inclusive = false, message = "Цена должна быть больше 0")
    var pricePerDay: BigDecimal? = null,

    @field:NotBlank(message = "Введите описание")
    @field:Size(max = 500)
    var description: String = "",

    @field:NotNull(message = "Выберите статус")
    var status: ListingStatus? = ListingStatus.AVAILABLE
)
