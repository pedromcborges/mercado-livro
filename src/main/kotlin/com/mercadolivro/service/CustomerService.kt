package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val repository: CustomerRepository
) {

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return repository.findAllByNameContaining(name)
        }
        return repository.findAll().toList()
    }

    fun getCustomer(id: Int): CustomerModel {
        return repository.findById(id).orElseThrow()
    }

    fun create(customer: CustomerModel) {
        repository.save(customer)
    }

    fun update(customer: CustomerModel) {
        if(!repository.existsById(customer.id!!)) {
            throw Exception()
        }

        repository.save(customer)
    }

    fun delete(id: Int) {
        if(!repository.existsById(id)) {
            throw Exception()
        }

        repository.deleteById(id)
    }
}