package com.heedoitdox.searchblogservice.exception

import com.heedoitdox.searchblogservice.config.Slf4j
import com.heedoitdox.searchblogservice.config.Slf4j.Companion.log
import feign.FeignException
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@Slf4j
@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(IllegalArgumentException::class, IllegalStateException::class)
    fun handleBadRequestException(ex: RuntimeException): ResponseEntity<ErrorResponse> {
        log.error("message", ex)
        val errorCode = CommonErrorCode.BAD_REQUEST
        return makeResponseEntity(errorCode)
    }

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(ex: Exception): ResponseEntity<ErrorResponse> {
        log.error("message", ex)
        val errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR
        return makeResponseEntity(errorCode)
    }

    @ExceptionHandler(FeignException::class)
    fun handleFeignException(ex: FeignException): ResponseEntity<ErrorResponse> {
        log.error("message: ", ex)
        val errorCode = CommonErrorCode.POORLY_CONNECT_INFRASTRUCTURE
        return makeResponseEntity(errorCode)
    }

    @ExceptionHandler(RequestParamBindException::class)
    fun handleRequestParamBindException(ex: RequestParamBindException): ResponseEntity<ErrorResponse> {
        log.error("message", ex)
        return ResponseEntity.status(ex.errorCode.status)
            .body(makeErrorResponse(ex.errors, ex.errorCode))
    }

    private fun makeResponseEntity(errorCode: ErrorCode): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(errorCode.status)
            .body(
                ErrorResponse(
                    code = errorCode.code,
                    message = errorCode.message
                )
            )

    private fun makeErrorResponse(errors: List<FieldError>, errorCode: ErrorCode): ErrorResponse {
        return ErrorResponse(
            code = errorCode.code,
            message = errorCode.message,
            errors = errors.map(ValidationError::of)
        )
    }
}
