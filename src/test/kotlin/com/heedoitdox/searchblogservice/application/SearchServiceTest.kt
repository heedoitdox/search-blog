package com.heedoitdox.searchblogservice.application

import com.heedoitdox.searchblogservice.domain.SearchKeywordRepository
import com.heedoitdox.searchblogservice.domain.createKakaoResponseDocument
import com.heedoitdox.searchblogservice.domain.createKakaoResponseMeta
import com.heedoitdox.searchblogservice.domain.createKakaoSearchBlogResponse
import com.heedoitdox.searchblogservice.domain.createSearchKeyword
import com.heedoitdox.searchblogservice.domain.createSearchRequest
import com.heedoitdox.searchblogservice.external.feign.client.KakaoClient
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.mockk.every
import io.mockk.mockk

class SearchServiceTest : StringSpec({
    val kakaoClient = mockk<KakaoClient>()
    val searchKeywordRepository = mockk<SearchKeywordRepository>()

    val searchService = SearchService(
        kakaoClient,
        searchKeywordRepository
    )

    "검색어로 블로그 목록을 조회할 경우 Pagination 된 목록이 반환된다" {
        val request = createSearchRequest()
        val clientResponse = createKakaoSearchBlogResponse(
            createKakaoResponseMeta(),
            listOf(
                createKakaoResponseDocument(),
                createKakaoResponseDocument(),
                createKakaoResponseDocument(),
                createKakaoResponseDocument()
            )
        )
        every { kakaoClient.searchBlog(any()) } returns clientResponse
        every { searchKeywordRepository.findByName(any()) } returns createSearchKeyword()

        val actual = searchService.search(request)

        actual.content shouldHaveSize 4
    }

    "10개 이상의 검색어가 저장되어있을 때, 10개의 인기 검색어를 조회할 경우 10개의 검색어가 반환된다." {
        every { searchKeywordRepository.findTop10ByOrderByNumberOfSearchDesc() } returns listOf(
            createSearchKeyword(),
            createSearchKeyword(),
            createSearchKeyword(),
            createSearchKeyword(),
            createSearchKeyword(),
            createSearchKeyword(),
            createSearchKeyword(),
            createSearchKeyword(),
            createSearchKeyword(),
            createSearchKeyword()
        )

        val actual = searchService.getSearchKeywordsTop10()

        actual.keywords shouldHaveSize 10
    }
})
