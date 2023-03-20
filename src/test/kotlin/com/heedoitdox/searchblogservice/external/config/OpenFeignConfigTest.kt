package com.heedoitdox.searchblogservice.external.config

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients
@EnableAutoConfiguration
class OpenFeignConfigTest
