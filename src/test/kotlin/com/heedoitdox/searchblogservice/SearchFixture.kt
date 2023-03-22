package com.heedoitdox.searchblogservice.domain

import com.heedoitdox.searchblogservice.application.SearchKeywordResponse
import com.heedoitdox.searchblogservice.application.SearchRequest
import com.heedoitdox.searchblogservice.application.SearchResponse
import com.heedoitdox.searchblogservice.external.feign.client.KakaoSearchBlogResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

private const val DEFAULT_NUMBER_OF_SEARCH = 1L
private const val DEFAULT_KEYWORD = "김밥"
private const val DEFAULT_PAGE = 1
private const val DEFAULT_SIZE = 10
private const val DEFAULT_DOCUMENT_TITLE = "[Spring Boot] <b>스프링</b><b>부트</b> <b>3.0</b>.X 버전을 사용하려고할 때 주의할 점(Kotlin/Java 동일)"
private const val DEFAULT_DOCUMENT_CONTENTS = "declares an API of a component compatible with Java 17 and the consumer needed a runtime of a component compatible with Java 11 쉽게 말해서 <b>스프링</b> <b>부트</b> <b>3.0</b>.X는 Java 17 버전과 호환되는 컴포넌트 API를 선언했는데 Java 11 버전과 호환되는 컴포넌트를 지정해서 호환되지 않는다는 것이다. 다시 말해, 국내..."
private const val DEFAULT_DOCUMENT_URL = "http://colabear754.tistory.com/101"
private const val DEFAULT_DOCUMENT_BLOGNAME = "개발하는 곰돌이"
private const val DEFAULT_DOCUMENT_THUMBNAIL = "https://search1.kakaocdn.net/argon/130x130_85_c/BfeXY0sFCTk"
private const val DEFAULT_DOCUMENT_DATETIME = "2023-01-17T11:49:50.000+09:00"
private const val DEFAULT_META_TOTAL_COUNT = 200
private const val DEFAULT_META_PAGEABLE_COUNT = 3
private const val DEFAULT_META_ID_END = false

fun createSearchKeyword(
    name: String? = DEFAULT_KEYWORD,
    numberOfSearch: Long = DEFAULT_NUMBER_OF_SEARCH,
    id: Long = 0L
): SearchKeyword = SearchKeyword(name = name!!, numberOfSearch = numberOfSearch, id = id)

fun createSearchKeywordResponse(
    searchKeywords: List<SearchKeyword>
): SearchKeywordResponse = SearchKeywordResponse.from(searchKeywords)

fun createSearchRequest(
    query: String? = DEFAULT_KEYWORD,
    page: Int? = DEFAULT_PAGE,
    size: Int? = DEFAULT_SIZE,
    sort: String? = null

): SearchRequest = SearchRequest(query!!, page, size, sort)

fun createSearchResponse(document: KakaoSearchBlogResponse.Document): SearchResponse = SearchResponse.from(document)

fun createPageSearchResponse(
    clientResponse: List<SearchResponse>,
    pageable: Pageable,
    totalCount: Long
): Page<SearchResponse> {
    return PageImpl(clientResponse, pageable, totalCount)
}

fun createKakaoSearchBlogResponse(
    meta: KakaoSearchBlogResponse.Meta,
    documents: List<KakaoSearchBlogResponse.Document>
): KakaoSearchBlogResponse = KakaoSearchBlogResponse(meta, documents)

fun createKakaoResponseMeta(
    totalCount: Int? = DEFAULT_META_TOTAL_COUNT,
    pageableCount: Int? = DEFAULT_META_PAGEABLE_COUNT,
    isEnd: Boolean? = DEFAULT_META_ID_END
): KakaoSearchBlogResponse.Meta = KakaoSearchBlogResponse.Meta(
    totalCount!!,
    pageableCount!!,
    isEnd!!
)

fun createKakaoResponseDocument(
    title: String? = DEFAULT_DOCUMENT_TITLE,
    contents: String? = DEFAULT_DOCUMENT_CONTENTS,
    url: String? = DEFAULT_DOCUMENT_URL,
    blogname: String? = DEFAULT_DOCUMENT_BLOGNAME,
    thumbnail: String? = DEFAULT_DOCUMENT_THUMBNAIL,
    datetime: String? = DEFAULT_DOCUMENT_DATETIME
): KakaoSearchBlogResponse.Document = KakaoSearchBlogResponse.Document(
    title!!,
    contents!!,
    url!!,
    blogname!!,
    thumbnail!!,
    datetime!!
)
