package com.mercadolivro.controller.request

import com.mercadolivro.model.CustomerModel

data class UpdateCustomerRequest(
    var name: String,
    var email: String
) {
    fun toCustomerModel(previousCustomer: CustomerModel): CustomerModel {
        return CustomerModel(previousCustomer.id, this.name, this.email, previousCustomer.status)
    }
}