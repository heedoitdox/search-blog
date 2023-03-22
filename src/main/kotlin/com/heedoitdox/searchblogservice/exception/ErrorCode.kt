package com.heedoitdox.searchblogservice.exception

import org.springframework.http.HttpStatus

interface ErrorCode {
    val status: HttpStatus
    val message: String
    val code: String
}

