package com.heedoitdox.searchblogservice.exception

import com.heedoitdox.searchblogservice.external.exception.KakaoClientHandledException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        logger.error("message", ex)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse(
                code = status.name,
                message = "입력 값이 올바르지 않습니다."
            )
        )
    }

    @ExceptionHandler(IllegalArgumentException::class, IllegalStateException::class)
    fun handleBadRequestException(ex: RuntimeException): ResponseEntity<ErrorResponse> {
        logger.error("message", ex)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                ErrorResponse(
                    code = HttpStatus.BAD_REQUEST.name,
                    message = ex.message
                )
            )
    }

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(ex: Exception): ResponseEntity<ErrorResponse> {
        logger.error("message", ex)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ErrorResponse(
                    code = HttpStatus.INTERNAL_SERVER_ERROR.name,
                    message = ex.message
                )
            )
    }

    @ExceptionHandler(KakaoClientHandledException::class)
    fun handleKakaoClientHandledException(ex: KakaoClientHandledException): ResponseEntity<ErrorResponse> {
        logger.error("message", ex)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ErrorResponse(
                    code = HttpStatus.INTERNAL_SERVER_ERROR.name,
                    message = ex.message
                )
            )
    }

    @ExceptionHandler(RequestParamBindException::class)
    fun handleRequestParamBindException(ex: RequestParamBindException): ResponseEntity<ErrorResponse> {
        logger.error("message", ex)
        return ResponseEntity.status(ex.errorCode.status)
            .body(makeErrorResponse(ex.errors, ex.errorCode))
    }

    private fun makeErrorResponse(errors: List<FieldError>, errorCode: ErrorCode): ErrorResponse {
        return ErrorResponse(
            code = errorCode.name,
            message = errorCode.message,
            errors = errors.map(ValidationError::of)
        )
    }
}
