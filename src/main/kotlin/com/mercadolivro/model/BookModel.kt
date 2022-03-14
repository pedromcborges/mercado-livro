package com.mercadolivro.model

import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.exception.BadRequestException
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "book")
data class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var title: String,

    @Column
    var price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null
) {
    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if (field == BookStatus.CANCELED || field == BookStatus.DELETED) {
                throw BadRequestException(ErrorCode.BOOK_CANNOT_BE_UPDATED_WITH_STATUS).withParameters(field!!.name)
            }
            field = value
        }

    constructor(
        id: Int? = null,
        title: String,
        price: BigDecimal,
        customer: CustomerModel? = null,
        status: BookStatus? = null
    ) : this(id, title, price, customer) {
        this.status = status
    }

    fun toResponse(): BookResponse {
        return BookResponse(
            id = this.id,
            title = this.title,
            price = this.price,
            status = this.status,
            customer = this.customer
        )
    }
}