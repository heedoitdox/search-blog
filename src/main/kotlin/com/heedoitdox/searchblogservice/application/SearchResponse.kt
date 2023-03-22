package com.heedoitdox.searchblogservice.application

import com.heedoitdox.searchblogservice.external.feign.client.KakaoSearchBlogResponse

data class SearchResponse(
    val title: String,
    val contents: String,
    val url: String,
    val blogname: String,
    val thumbnail: String,
    val datetime: String
) {
    companion object {
        fun from(clientResponse: KakaoSearchBlogResponse.Document): SearchResponse = with(clientResponse) {
            SearchResponse(title, contents, url, blogname, thumbnail, datetime)
        }
    }
}
