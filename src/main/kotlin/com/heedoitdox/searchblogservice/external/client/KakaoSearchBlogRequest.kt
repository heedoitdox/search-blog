package com.heedoitdox.searchblogservice.external.client

data class KakaoSearchBlogRequest(
    val query: String,
    val page: Int?,
    val size: Int?,
    val sort: String?
)
