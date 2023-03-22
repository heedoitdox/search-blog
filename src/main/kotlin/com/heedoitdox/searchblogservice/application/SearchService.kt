package com.heedoitdox.searchblogservice.application

import com.heedoitdox.searchblogservice.domain.SearchKeyword
import com.heedoitdox.searchblogservice.domain.SearchKeywordRepository
import com.heedoitdox.searchblogservice.external.feign.client.KakaoClient
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SearchService(
    private val kakaoClient: KakaoClient,
    private val searchKeywordRepository: SearchKeywordRepository
) {
    fun search(request: SearchRequest): Page<SearchResponse> {
        request.queryTrim()
        val clientResponse = kakaoClient.searchBlog(request.toKakaoSearchBlogRequest())
        val pageable: Pageable = PageRequest.of(request.page!!, request.size!!)
        val response = clientResponse.documents.map(SearchResponse::from)

        val searchKeyword = searchKeywordRepository.findByName(request.query)
        if (searchKeyword == null) {
            searchKeywordRepository.save(SearchKeyword(request.query))
        } else {
            searchKeyword.addOneNumberOfSearch()
        }

        return PageImpl(response, pageable, clientResponse.meta.totalCount.toLong())
    }

    fun getSearchKeywordsTop10(): SearchKeywordResponse {
        val searchKeywords = searchKeywordRepository.findTop10ByOrderByNumberOfSearchDesc()

        return SearchKeywordResponse.from(searchKeywords)
    }
}
