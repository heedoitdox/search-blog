package com.heedoitdox.searchblogservice.external.feign.exception

import feign.Response
import feign.codec.ErrorDecoder

class FeignErrorDecoder() : ErrorDecoder {
    override fun decode(methodKey: String, response: Response): Exception {
        return ErrorDecoder.Default().decode(methodKey, response)
    }
}
