package com.app.credit.application.service.impl

import com.app.credit.application.entity.Customer
import com.app.credit.application.exceptions.BussinessException
import com.app.credit.application.repositories.CustomerRepository
import com.app.credit.application.service.ICustomerService;
import org.springframework.stereotype.Service

@Service
class CustomerService(
        private val repository:CustomerRepository
):ICustomerService {
    override fun save(customer: Customer): Customer =
        this.repository.save(customer)


    override fun findById(id: Long): Customer = repository.findById(id).orElseThrow{throw BussinessException("Customer não encontrado")}


    override fun delete(id: Long) {
        val customer: Customer = this.findById(id)
        this.repository.delete(customer)
    }

}