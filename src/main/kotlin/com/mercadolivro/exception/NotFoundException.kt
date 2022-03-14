package com.mercadolivro.exception

import com.mercadolivro.model.ErrorCode

class NotFoundException(
    val errorCode: ErrorCode,
    private var parameters: Array<String>? = null
): Exception() {
    fun withParameters(vararg params: String): NotFoundException {
        return apply { parameters = params as Array<String>? }
    }

    fun getParameters(): Array<String>? {
        return this.parameters
    }
}