package com.mercadolivro.controller.request

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.CustomerModel

data class CreateCustomerRequest(
    var name: String,
    var email: String
) {
    fun toCustomerModel(): CustomerModel {
        return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ACTIVE)
    }
}