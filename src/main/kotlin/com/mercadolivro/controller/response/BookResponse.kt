package com.mercadolivro.controller.response

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.CustomerModel
import java.math.BigDecimal

class BookResponse(
    var id: Int? = null,
    var title: String,
    var price: BigDecimal,
    var status: BookStatus? = null,
    var customer: CustomerModel? = null
)