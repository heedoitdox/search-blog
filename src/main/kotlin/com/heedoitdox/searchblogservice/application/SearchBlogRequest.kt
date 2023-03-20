package com.heedoitdox.searchblogservice.application

import com.heedoitdox.searchblogservice.external.client.KakaoSearchBlogRequest
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class SearchBlogRequest(
    @field:NotNull @field:NotBlank
    val query: String,
    @field:Min(1)
    @field:Max(50)
    val page: Int? = 1,
    @field:Min(1)
    @field:Max(50)
    val size: Int? = 10,
    val sort: String?
) {
    fun toKakaoSearchBlogRequest(): KakaoSearchBlogRequest =
        KakaoSearchBlogRequest(query, page, size, sort)
}
