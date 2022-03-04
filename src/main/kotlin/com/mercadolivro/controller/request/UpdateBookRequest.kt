package com.mercadolivro.controller.request

import com.mercadolivro.model.BookModel
import java.math.BigDecimal

data class UpdateBookRequest(
    var title: String?,
    var price: BigDecimal?
) {
    fun toBookModel(previousBook: BookModel): BookModel {
        return BookModel(
            previousBook.id,
            this.title ?: previousBook.title,
            this.price ?: previousBook.price,
            previousBook.status,
            previousBook.customer
        )
    }
}
