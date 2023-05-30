package com.app.credit.application.service.impl

import com.app.credit.application.entity.Credit
import com.app.credit.application.repositories.CreditRepository
import com.app.credit.application.service.ICreditService
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.time.LocalDate
import java.util.UUID

@Service
class CreditService(
        private val creditRepository: CreditRepository,
        private val customerService: CustomerService
) : ICreditService {

    override fun save(credit: Credit): Credit {
        this.validDayFirstInstallment(credit.firstDayInstallment)
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> =
            this.creditRepository.findAllByCustomerId(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = (this.creditRepository.findByCreditCode(creditCode)
                ?: throw RuntimeException("Creditcode $creditCode not found"))
        return if (credit.customer?.id == customerId) credit
        else throw IllegalArgumentException("Contact admin")

    }

    private fun validDayFirstInstallment(dayFirstInstallment: LocalDate): Boolean {
        return if (dayFirstInstallment.isBefore(LocalDate.now().plusMonths(3))) true
        else throw RuntimeException("Invalid Date")
    }
}