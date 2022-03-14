package com.mercadolivro.controller.request

import com.mercadolivro.model.CustomerModel
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class UpdateCustomerRequest(
    @field:NotEmpty(message = "{name.empty}")
    var name: String,

    @field:Email(message = "{email.invalid}")
    var email: String
) {
    fun toCustomerModel(previousCustomer: CustomerModel): CustomerModel {
        return CustomerModel(
            previousCustomer.id,
            this.name,
            this.email,
            previousCustomer.status)
    }
}