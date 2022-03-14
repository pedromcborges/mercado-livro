package com.mercadolivro.exception

import com.mercadolivro.controller.response.ErrorResponse
import com.mercadolivro.controller.response.FieldErrorResponse
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.util.Locale

@ControllerAdvice
class ControllerAdvice(val messageSource: MessageSource) {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val err = ErrorResponse(
            HttpStatus.NOT_FOUND.name,
            messageSource.getMessage(ex.errorCode.key, ex.getParameters(), Locale.ENGLISH),
            null)
        return ResponseEntity(err, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val err = ErrorResponse(
            HttpStatus.BAD_REQUEST.name,
            messageSource.getMessage(ex.errorCode.key, ex.getParameters(), Locale.ENGLISH),
            null)
        return ResponseEntity(err, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val err = ErrorResponse(
            HttpStatus.UNPROCESSABLE_ENTITY.name,
            "Invalid Request",
            ex.bindingResult.fieldErrors.map {
                FieldErrorResponse(it.defaultMessage ?: "invalid", it.field)
            })
        return ResponseEntity(err, HttpStatus.UNPROCESSABLE_ENTITY)
    }
}