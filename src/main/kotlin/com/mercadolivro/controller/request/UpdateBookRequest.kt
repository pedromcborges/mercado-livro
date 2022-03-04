package com.mercadolivro.controller.request

import com.mercadolivro.model.BookModel
import java.math.BigDecimal

data class UpdateBookRequest(
    var title: String?,
    var price: BigDecimal?
) {
    fun toBookModel(previousBook: BookModel): BookModel {
        return BookModel(
            id = previousBook.id,
            title = this.title ?: previousBook.title,
            price= this.price ?: previousBook.price,
            status = previousBook.status,
            customer = previousBook.customer
        )
    }
}
