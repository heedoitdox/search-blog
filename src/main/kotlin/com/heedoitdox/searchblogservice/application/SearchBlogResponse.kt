package com.heedoitdox.searchblogservice.application

import com.heedoitdox.searchblogservice.external.client.KakaoSearchBlogResponse

data class SearchBlogResponse(
    val title: String,
    val contents: String,
    val url: String,
    val blogname: String,
    val thumbnail: String,
    val datetime: String
) {
    companion object {
        fun from(clientResponse: KakaoSearchBlogResponse.Document): SearchBlogResponse = with(clientResponse) {
            SearchBlogResponse(title, contents, url, blogname, thumbnail, datetime)
        }
    }
}
