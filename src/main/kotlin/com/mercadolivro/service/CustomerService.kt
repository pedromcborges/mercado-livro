package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerService {

    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customers.filter { it.name.contains(name, true) }
        }
        return customers
    }

    fun getCustomer(id: String): CustomerModel {
        return customers.first { it.id == id }
    }

    fun create(customer: CustomerModel) {
        customer.id = customers.size.plus(1).toString()
        customers.add(customer)
    }

    fun update(customer: CustomerModel) {
        customers.first { it.id == customer.id }.let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun delete(id: String) {
        customers.removeIf { it.id == id }
    }
}