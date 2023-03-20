package com.heedoitdox.searchblogservice.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String
) {
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameters")
}
