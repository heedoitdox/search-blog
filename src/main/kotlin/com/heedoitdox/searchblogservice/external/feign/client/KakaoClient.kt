package com.heedoitdox.searchblogservice.external.feign.client

import com.heedoitdox.searchblogservice.external.feign.config.OpenFeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.cloud.openfeign.SpringQueryMap
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(
    name = "search-blog-api",
    url = "\${application.feign-url.search-blog-api.kakao}",
    configuration = [OpenFeignConfig::class]
)
interface KakaoClient {
    @GetMapping("/v2/search/blog")
    fun searchBlog(
        @SpringQueryMap request: KakaoSearchBlogRequest
    ): KakaoSearchBlogResponse
}
