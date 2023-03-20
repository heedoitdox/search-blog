package com.heedoitdox.searchblogservice.external.exception

import feign.Response
import feign.codec.ErrorDecoder

class KakaoClientErrorDecoder : ErrorDecoder {
    override fun decode(methodKey: String, response: Response): Exception {
        throw KakaoClientHandledException()
    }

    // TODO: https://www.baeldung.com/feign-retrieve-original-message
}
