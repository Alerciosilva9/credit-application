package com.app.credit.application.repositories

import com.app.credit.application.entity.Credit
import com.app.credit.application.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository: JpaRepository<Customer, Long>
