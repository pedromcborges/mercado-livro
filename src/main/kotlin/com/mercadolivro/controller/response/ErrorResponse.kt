package com.mercadolivro.controller.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include


@JsonInclude(Include.NON_NULL)
data class ErrorResponse(
    var errorCode: String?,
    var message: String?,
    var errors: List<FieldErrorResponse>?
)
