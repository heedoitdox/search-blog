package com.heedoitdox.searchblogservice.external.config

import feign.RequestInterceptor
import feign.RequestTemplate
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
}
