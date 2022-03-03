package com.mercadolivro.controller.request

import com.mercadolivro.model.CustomerModel

data class UpdateCustomerRequest(
    var name: String,
    var email: String
) {
    fun toCustomerModel(id: Int): CustomerModel {
        return CustomerModel(id, this.name, this.email)
    }
}