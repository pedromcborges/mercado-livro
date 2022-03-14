package com.mercadolivro.exception

import com.mercadolivro.controller.response.ErrorResponse
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.util.Locale

@ControllerAdvice
class ControllerAdvice(val messageSource: MessageSource) {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val err = ErrorResponse(
            ex.errorCode.name,
            messageSource.getMessage(ex.errorCode.key, ex.getParameters(), Locale.ENGLISH),
            null)
        return ResponseEntity(err, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val err = ErrorResponse(
            ex.errorCode.name,
            messageSource.getMessage(ex.errorCode.key, ex.getParameters(), Locale.ENGLISH),
            null)
        return ResponseEntity(err, HttpStatus.BAD_REQUEST)
    }
}