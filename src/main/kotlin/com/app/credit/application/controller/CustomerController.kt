package com.app.credit.application.controller

import com.app.credit.application.dtos.CustomerDTO
import com.app.credit.application.dtos.CustomerUpdateDto
import com.app.credit.application.dtos.CustomerView
import com.app.credit.application.entity.Customer
import com.app.credit.application.service.impl.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/customers")
class CustomerController (private val customerService:CustomerService) {

    @PostMapping
    fun save(@RequestBody @Valid dto:CustomerDTO):ResponseEntity<String>{
        var savedcustomer = this.customerService.save(dto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("${savedcustomer.email} foi salvo com sucesso")
    }
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Long) = this.customerService.delete(id)

    @PatchMapping
    fun upadateCustomer(
            @RequestParam(value = "customerId") id: Long,
            @RequestBody @Valid customerUpdateDto: CustomerUpdateDto
    ): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)
        val cutomerToUpdate: Customer = customerUpdateDto.toEntity(customer)
        val customerUpdated: Customer = this.customerService.save(cutomerToUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customerUpdated))
    }

}