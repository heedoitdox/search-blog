package com.heedoitdox.searchblogservice.exception

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.validation.FieldError

data class ErrorResponse(
    val code: String,
    val message: String?,

    @field:JsonInclude(JsonInclude.Include.NON_EMPTY)
    val errors: List<ValidationError> = arrayListOf()
)

data class ValidationError(
    val field: String? = null,
    val message: String? = null
) {
    companion object {
        fun of(fieldError: FieldError): ValidationError =
            ValidationError(fieldError.field, fieldError.defaultMessage)
    }
}
