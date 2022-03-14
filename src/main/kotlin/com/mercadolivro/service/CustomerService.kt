package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.model.ErrorCode
import com.mercadolivro.repository.BookRepository
import com.mercadolivro.repository.CustomerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookRepository: BookRepository
) {

    fun findAll(name: String?, pageable: Pageable): Page<CustomerModel> {
        name?.let {
            return customerRepository.findAllByNameContaining(name, pageable)
        }
        return customerRepository.findAll(pageable)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id)
            .orElseThrow { NotFoundException(ErrorCode.CUSTOMER_NOT_EXISTS).withParameters(id.toString()) }
    }

    fun create(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun update(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }

        customerRepository.save(customer)
    }

    @Transactional
    fun delete(id: Int) {
        val customer = customerRepository.findById(id)
            .orElseThrow { NotFoundException(ErrorCode.CUSTOMER_NOT_EXISTS).withParameters(id.toString()) }

        val customerBooks = bookRepository.findByCustomer(customer)
        customerBooks.forEach {
            it.status = BookStatus.DELETED
        }
        bookRepository.saveAll(customerBooks)

        customer.status = CustomerStatus.INACTIVE

        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)
    }
}