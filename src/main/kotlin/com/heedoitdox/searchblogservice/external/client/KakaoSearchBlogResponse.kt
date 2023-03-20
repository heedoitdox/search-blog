package com.heedoitdox.searchblogservice.external.client

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KakaoSearchBlogResponse(
    val meta: Meta,
    val documents: List<Document>
) {
    @Serializable
    data class Meta(
        @SerialName("total_count") val totalCount: Int,
        @SerialName("pageable_count") val pageableCount: Int,
        @SerialName("is_end") val isEnd: Boolean
    )

    @Serializable
    data class Document(
        val title: String,
        val contents: String,
        val url: String,
        val blogname: String,
        val thumbnail: String,
        val datetime: String
    )
}
