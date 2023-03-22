package com.heedoitdox.searchblogservice.external.feign.client

data class KakaoSearchBlogRequest(
    val query: String,
    val page: Int?,
    val size: Int?,
    val sort: String?
) {
    init {
        require(query.isNotBlank()) { "query is not black." }
    }
}
