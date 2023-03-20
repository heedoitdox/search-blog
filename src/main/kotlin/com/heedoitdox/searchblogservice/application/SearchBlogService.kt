package com.heedoitdox.searchblogservice.application

import com.heedoitdox.searchblogservice.external.client.KakaoClient
import com.heedoitdox.searchblogservice.external.client.KakaoSearchBlogResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SearchBlogService(
    private val kakaoClient: KakaoClient
) {
    fun search(request: SearchBlogRequest): Page<KakaoSearchBlogResponse.Document> {
        val response = kakaoClient.searchBlog(request.toKakaoSearchBlogRequest())
        val pageable: Pageable = PageRequest.of(request.page!!, request.size!!)

        return PageImpl(response.documents, pageable, response.meta.totalCount.toLong())
    }
}
