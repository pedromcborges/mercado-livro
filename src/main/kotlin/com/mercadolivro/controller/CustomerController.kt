package com.mercadolivro.controller

import com.mercadolivro.controller.request.CreateCustomerRequest
import com.mercadolivro.controller.request.UpdateCustomerRequest
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
class CustomerController(
    val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody createCustomerRequest: CreateCustomerRequest) {
        customerService.create(createCustomerRequest.toCustomerModel())
    }

    @GetMapping
    fun findAll(@RequestParam name: String?): List<CustomerModel> {
        return customerService.findAll(name)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): CustomerModel {
        return customerService.findById(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody updateCustomerRequest: UpdateCustomerRequest) {
        val savedUser = customerService.findById(id)
        customerService.update(updateCustomerRequest.toCustomerModel(savedUser))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }
}