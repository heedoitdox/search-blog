package com.heedoitdox.searchblogservice.exception

import org.springframework.validation.FieldError

class RequestParamBindException(
    val errorCode: ErrorCode,
    val errors: List<FieldError> = emptyList()
) : RuntimeException()
