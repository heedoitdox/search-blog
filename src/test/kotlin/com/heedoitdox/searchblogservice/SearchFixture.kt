package com.heedoitdox.searchblogservice.domain

import com.heedoitdox.searchblogservice.application.SearchRequest
import com.heedoitdox.searchblogservice.external.feign.client.KakaoSearchBlogResponse

private const val DEFAULT_NUMBER_OF_SEARCH = 1L
private const val DEFAULT_KEYWORD = "김밥"
private const val DEFAULT_PAGE = 1
private const val DEFAULT_SIZE = 10
private const val DEFAULT_DOCUMENT_TITLE = "주말에 먹기 좋은 김밥요리 두<b>가지</b> 전과 김<b>볶</b>김밥만드는법"
private const val DEFAULT_DOCUMENT_CONTENTS = "\"안녕하세요 김밥요리 두<b>가지</b> 만드는 푸드인플루언서 현실쭈이 입니다"
private const val DEFAULT_DOCUMENT_URL = "https://blog.naver.com/charmingrl/223048524178"
private const val DEFAULT_DOCUMENT_BLOGNAME = "현실적이고 편한 집밥요리 라이프"
private const val DEFAULT_DOCUMENT_THUMBNAIL = "https://search4.kakaocdn.net/argon/130x130_85_c/KRapXmrrRLW"
private const val DEFAULT_DOCUMENT_DATETIME = "2023-03-18T16:56:00.000+09:00"
private const val DEFAULT_META_TOTAL_COUNT = 200
private const val DEFAULT_META_PAGEABLE_COUNT = 3
private const val DEFAULT_META_ID_END = false

fun createSearchKeyword(
    name: String? = DEFAULT_KEYWORD,
    numberOfSearch: Long = DEFAULT_NUMBER_OF_SEARCH,
    id: Long = 0L
): SearchKeyword = SearchKeyword(name = name!!, numberOfSearch = numberOfSearch, id = id)

fun createSearchRequest(
    query: String? = DEFAULT_KEYWORD,
    page: Int? = DEFAULT_PAGE,
    size: Int? = DEFAULT_SIZE,
    sort: String? = null

): SearchRequest = SearchRequest(query!!, page, size, sort)

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
