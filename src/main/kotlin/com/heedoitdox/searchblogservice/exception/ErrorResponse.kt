package com.heedoitdox.searchblogservice.exception

import org.springframework.validation.FieldError

data class ErrorResponse(
    val status: String,
    val message: String?,
    val errors: List<ErrorItem> = arrayListOf()
)

data class ErrorItem(
    val field: String? = null,
    val message: String? = null
) {
    companion object {
        fun of(fieldError: FieldError): ErrorItem =
            ErrorItem(fieldError.field, fieldError.defaultMessage)
    }
}
