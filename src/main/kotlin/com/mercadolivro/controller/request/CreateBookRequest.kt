package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import java.math.BigDecimal

data class CreateBookRequest(
    var title: String,
    var price: BigDecimal,
    @JsonAlias("customer_id")
    var customerId: Int
) {
    fun toBookModel(customer: CustomerModel): BookModel {
        return BookModel(title = title, price = price, status = BookStatus.ACTIVE, costumerId = customer)
    }
}