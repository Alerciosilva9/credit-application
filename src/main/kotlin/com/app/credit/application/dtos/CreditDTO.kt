package com.app.credit.application.dtos

import com.app.credit.application.entity.Credit
import com.app.credit.application.entity.Customer
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate


data class CreditDTO(
        @field:NotNull(message = "Invalid input") val creditValue: BigDecimal,
        @field:Future val dayFirstOfInstallment: LocalDate,
        @field:Min(value = 1) @field:Max(value = 48) val numberOfInstallments: Int,
        @field:NotNull(message = "Invalid input") val customerId: Long
) {

    fun toEntity(): Credit = Credit(
            creditValue = this.creditValue,
            firstDayInstallment = this.dayFirstOfInstallment,
            numberOfInstallment = this.numberOfInstallments,
            customer = Customer(id = this.customerId)
    )

}