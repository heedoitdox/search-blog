package com.heedoitdox.searchblogservice.external.client

import com.heedoitdox.searchblogservice.external.config.OpenFeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.cloud.openfeign.SpringQueryMap
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "search-blog-api", url = "https://dapi.kakao.com", configuration = [OpenFeignConfig::class])
interface KakaoClient {
    @GetMapping("/v2/search/blogdes")
    fun searchBlog(
        @SpringQueryMap request: KakaoSearchBlogRequest
    ): KakaoSearchBlogResponse
}
