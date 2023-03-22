package com.heedoitdox.searchblogservice.exception

import org.springframework.http.HttpStatus

enum class SearchError(
    override val status: HttpStatus,
    override val message: String,
    override val code: String
): ErrorCode {
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameters", "INVALID_PARAMETER")
}