package com.heedoitdox.searchblogservice.application

import com.heedoitdox.searchblogservice.external.client.KakaoClient
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
    fun search(request: SearchBlogRequest): Page<SearchBlogResponse> {
        val clientResponse = kakaoClient.searchBlog(request.toKakaoSearchBlogRequest())

        val pageable: Pageable = PageRequest.of(request.page!!, request.size!!)
        val response = clientResponse.documents.map(SearchBlogResponse::from)

        return PageImpl(response, pageable, clientResponse.meta.totalCount.toLong())
    }
}
