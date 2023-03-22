package com.heedoitdox.searchblogservice.application

import com.heedoitdox.searchblogservice.external.feign.client.KakaoSearchBlogRequest
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class SearchRequest(
    @field:NotNull @field:NotBlank
    var query: String,
    @field:Min(1)
    @field:Max(50)
    val page: Int? = DEFAULT_PAGE,
    @field:Min(1)
    @field:Max(50)
    val size: Int? = DEFAULT_SIZE,
    val sort: String?
) {
    fun queryTrim() {
        this.query = query.trim()
    }

    fun toKakaoSearchBlogRequest(): KakaoSearchBlogRequest = KakaoSearchBlogRequest(query, page, size, sort)

    companion object {
        private const val DEFAULT_PAGE = 1
        private const val DEFAULT_SIZE = 10
    }
}
