package com.mercadolivro.model

enum class ErrorCode(val key: String) {
    BOOK_NOT_EXISTS("book.not.exists"),
    BOOK_CANNOT_BE_UPDATED_WITH_STATUS("book.cannot.be.updated.with.status"),
    CUSTOMER_NOT_EXISTS("customer.not.exists")
}