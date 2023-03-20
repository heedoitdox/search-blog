package com.heedoitdox.searchblogservice.application

import com.heedoitdox.searchblogservice.external.client.KakaoSearchBlogRequest
import kotlinx.serialization.Serializable

@Serializable
data class SearchBlogRequest(
    val query: String,
    val page: Int? = 1,
    val size: Int? = 10,
    val sort: String?
) {
    fun toKakaoSearchBlogRequest(): KakaoSearchBlogRequest =
        KakaoSearchBlogRequest(query, page, size, sort)
}
