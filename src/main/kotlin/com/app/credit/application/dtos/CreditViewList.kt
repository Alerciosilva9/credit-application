package com.app.credit.application.dtos

import com.app.credit.application.entity.Credit
import java.math.BigDecimal
import java.util.UUID

data class CreditViewList(
        val creditCode: UUID,
        val creditValue: BigDecimal,
        val numberOfInstallments: Int
) {

    //RECEBE CREDIT e converte para CreditViewList para o MAP do STREAM
    constructor(credit: Credit) : this(
            creditCode = credit.creditCode,
            creditValue = credit.creditValue,
            numberOfInstallments = credit.numberOfInstallment
    )
}