package com.heedoitdox.searchblogservice.external.feign.config

import com.heedoitdox.searchblogservice.external.feign.exception.FeignErrorDecoder
import feign.RequestInterceptor
import feign.RequestTemplate
import feign.codec.ErrorDecoder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenFeignConfig {
    @Value("\${kakao.rest-api-key}")
    lateinit var apiKey: String

    @Bean
    fun kakaoRequestInterceptor(): RequestInterceptor {
        return RequestInterceptor { template: RequestTemplate ->
            template.header("Authorization", "KakaoAK $apiKey")
        }
    }

    @Bean
    fun errorDecoder(): ErrorDecoder {
        return FeignErrorDecoder()
    }
}
