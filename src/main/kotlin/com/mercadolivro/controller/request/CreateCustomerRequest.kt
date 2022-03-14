package com.mercadolivro.controller.request

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.CustomerModel
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class CreateCustomerRequest(
    @field:NotEmpty(message = "{name.empty}")
    var name: String,

    @field:Email(message = "{email.invalid}")
    var email: String
) {
    fun toCustomerModel(): CustomerModel {
        return CustomerModel(
            name = this.name,
            email = this.email,
            status = CustomerStatus.ACTIVE)
    }
}