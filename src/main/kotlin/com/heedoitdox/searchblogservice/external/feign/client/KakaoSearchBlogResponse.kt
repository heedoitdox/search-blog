package com.heedoitdox.searchblogservice.external.feign.client

data class KakaoSearchBlogResponse(
    val meta: Meta,
    val documents: List<Document>
) {
    // TODO: snake_case parsing
    data class Meta(
        val totalCount: Int,
        val pageableCount: Int,
        val isEnd: Boolean
    )

    data class Document(
        val title: String,
        val contents: String,
        val url: String,
        val blogname: String,
        val thumbnail: String,
        val datetime: String
    )
}
