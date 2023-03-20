package com.heedoitdox.searchblogservice.external.client

import kotlinx.serialization.Serializable

@Serializable
data class KakaoSearchBlogRequest(
    val query: String,
    val page: Int?,
    val size: Int?,
    val sort: String?
)
