package com.mercadolivro.controller

import com.mercadolivro.controller.request.CreateBookRequest
import com.mercadolivro.controller.request.UpdateBookRequest
import com.mercadolivro.model.BookModel
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("book")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody createBookRequest: CreateBookRequest) {
        val customer = customerService.findById(createBookRequest.customerId)
        bookService.create(createBookRequest.toBookModel(customer))
    }

    @GetMapping
    fun findAll(): List<BookModel> {
        return bookService.findAll()
    }

    @GetMapping("/active")
    fun findActives(): List<BookModel> {
        return bookService.findActives()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): BookModel {
        return bookService.findById(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody updateBookRequest: UpdateBookRequest) {
        val savedBook = bookService.findById(id)
        bookService.update(updateBookRequest.toBookModel(savedBook))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        bookService.delete(id)
    }
}