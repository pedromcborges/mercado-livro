package com.mercadolivro.model

import com.mercadolivro.controller.response.CustomerResponse
import com.mercadolivro.enums.CustomerStatus
import javax.persistence.*

@Entity(name = "customer")
data class CustomerModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var email: String,

    @Column
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus
) {
    fun toResponse(): CustomerResponse {
        return CustomerResponse(
            id = this.id,
            name = this.name,
            email = this.email,
            status = this.status
        )
    }
}