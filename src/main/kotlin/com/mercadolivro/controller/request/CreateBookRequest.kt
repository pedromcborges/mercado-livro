package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import java.math.BigDecimal
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CreateBookRequest(
    @field:NotEmpty(message = "{title.empty}")
    var title: String,

    @field:NotNull(message = "{price.null}")
    @field:Min(value = 0, message = "{price.min.value}")
    var price: BigDecimal,

    @JsonAlias("customer_id")
    var customerId: Int
) {
    fun toBookModel(customer: CustomerModel): BookModel {
        return BookModel(
            title = title,
            price = price,
            status = BookStatus.ACTIVE,
            customer = customer)
    }
}