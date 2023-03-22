package com.heedoitdox.searchblogservice.exception

import org.springframework.http.HttpStatus

enum class CommonErrorCode(
    override val status: HttpStatus,
    override val message: String,
    override val code: String
) : ErrorCode {
    POORLY_CONNECT_INFRASTRUCTURE(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "Poor connectivity to external infrastructure",
        "POORLY_CONNECT_INFRASTRUCTURE"
    ),
    INVALID_REQUEST_PARAMETERS(
        HttpStatus.BAD_REQUEST,
        "invalid request parameter",
        "INVALID_REQUEST_PARAMETERS"
    ),
    INTERNAL_SERVER_ERROR(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "internal server error",
        "INTERNAL_SERVER_ERROR"
    ),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "invalid request body", "BAD_REQUEST")
}
