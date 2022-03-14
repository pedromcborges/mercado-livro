package com.mercadolivro.controller

import com.mercadolivro.controller.request.CreateCustomerRequest
import com.mercadolivro.controller.request.UpdateCustomerRequest
import com.mercadolivro.controller.response.CustomerResponse
import com.mercadolivro.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("customers")
class CustomerController(
    val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid createCustomerRequest: CreateCustomerRequest) {
        customerService.create(createCustomerRequest.toCustomerModel())
    }

    @GetMapping
    fun findAll(@RequestParam name: String?,
                @PageableDefault(page = 0, size = 10) pageable: Pageable): Page<CustomerResponse> {
        return customerService.findAll(name, pageable).map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): CustomerResponse {
        return customerService.findById(id).toResponse()
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