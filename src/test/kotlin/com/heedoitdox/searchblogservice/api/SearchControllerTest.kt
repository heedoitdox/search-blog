package com.heedoitdox.searchblogservice.api

import com.heedoitdox.searchblogservice.application.SearchService
import com.heedoitdox.searchblogservice.domain.createKakaoResponseDocument
import com.heedoitdox.searchblogservice.domain.createPageSearchResponse
import com.heedoitdox.searchblogservice.domain.createSearchKeyword
import com.heedoitdox.searchblogservice.domain.createSearchKeywordResponse
import com.heedoitdox.searchblogservice.domain.createSearchResponse
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.domain.PageRequest
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.test.web.servlet.get

@WebMvcTest
class SearchControllerTest : RestControllerTest() {

    @MockkBean
    private lateinit var searchService: SearchService

    @Test
    fun `검색어 조회 요청을 하면 검색결과와 페이지네이션된 응답을 반환한다`() {
        val response = createPageSearchResponse(
            clientResponse = listOf(
                createSearchResponse(createKakaoResponseDocument())
            ),
            pageable = PageRequest.of(1, 10),
            totalCount = 200
        )
        every { searchService.search(any()) } returns response

        mockMvc.get("/api/search/blog") {
            param("query", "스프링부트 3.0")
            param("page", "1")
            param("size", "10")
            param("sort", "accuracy")
        }
            .andExpect {
                status { isOk() }
                content { response }
            }.andDo {
                handle(
                    document(
                        "get-search",
                        RequestDocumentation.requestParameters(
                            parameterWithName("query").description("조회할 검색어"),
                            parameterWithName("page").description("조회할 페이지"),
                            parameterWithName("size").description("조회할 사이즈"),
                            parameterWithName("sort").description("정렬 기준")
                        ),
                        responseFields(
                            fieldWithPath("content").type(JsonFieldType.ARRAY).description("결과 데이터"),
                            fieldWithPath("content[].title").type(JsonFieldType.STRING).description("글 제목"),
                            fieldWithPath("content[].contents").type(JsonFieldType.STRING).description("내용"),
                            fieldWithPath("content[].url").type(JsonFieldType.STRING)
                                .description("URL"),
                            fieldWithPath("content[].blogname").type(JsonFieldType.STRING).description(
                                "블로그 이름"
                            ),
                            fieldWithPath("content[].thumbnail").type(JsonFieldType.STRING)
                                .description("썸네일"),
                            fieldWithPath("content[].datetime").type(JsonFieldType.STRING)
                                .description("글 작성 시간"),
                            fieldWithPath("totalElements").type(JsonFieldType.NUMBER)
                                .description("전체 데이터 수"),
                            fieldWithPath("totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수"),
                            fieldWithPath("first").type(JsonFieldType.BOOLEAN).description("첫번째 여부"),
                            fieldWithPath("last").type(JsonFieldType.BOOLEAN).description("마지막 여부"),
                            fieldWithPath("numberOfElements").type(JsonFieldType.NUMBER)
                                .description("데이터의 수"),
                            fieldWithPath("size").type(JsonFieldType.NUMBER).description("사이즈"),
                            fieldWithPath("number").type(JsonFieldType.NUMBER).description("번호"),
                            fieldWithPath("sort").type(JsonFieldType.OBJECT).description("정렬"),
                            fieldWithPath("empty").type(JsonFieldType.BOOLEAN).description("비어있음 여부"),
                            fieldWithPath("sort.sorted").type(JsonFieldType.BOOLEAN).description("정렬됨 여부"),
                            fieldWithPath("sort.unsorted").type(JsonFieldType.BOOLEAN)
                                .description("정렬안됨 여부"),
                            fieldWithPath("sort.empty").type(JsonFieldType.BOOLEAN).description("비어있음 여부"),
                            fieldWithPath("pageable").type(JsonFieldType.OBJECT).description("-"),
                            fieldWithPath("pageable.sort").type(JsonFieldType.OBJECT).description("pageable 정렬"),
                            fieldWithPath("pageable.sort.empty").type(JsonFieldType.BOOLEAN).description("비어있음 여부"),
                            fieldWithPath("pageable.sort.sorted").type(JsonFieldType.BOOLEAN).description("정렬 여부"),
                            fieldWithPath("pageable.sort.unsorted").type(JsonFieldType.BOOLEAN).description("정렬안됨 여부"),
                            fieldWithPath("pageable.offset").type(JsonFieldType.NUMBER).description("offset"),
                            fieldWithPath("pageable.pageNumber").type(JsonFieldType.NUMBER).description("페이지 넘버"),
                            fieldWithPath("pageable.pageSize").type(JsonFieldType.NUMBER).description("페이지 사이즈"),
                            fieldWithPath("pageable.paged").type(JsonFieldType.BOOLEAN).description("paged"),
                            fieldWithPath("pageable.unpaged").type(JsonFieldType.BOOLEAN).description("unpaged")
                        )
                    )
                )
            }
    }

    @Test
    fun `인기검색어 조회 요청을 하면 인기 검색어 목록을 반환한다`() {
        val response = createSearchKeywordResponse(
            listOf(
                createSearchKeyword(name = "백종원 떡볶이", numberOfSearch = 234L, id = 23),
                createSearchKeyword(name = "코프링", numberOfSearch = 211L, id = 77),
                createSearchKeyword(name = "kotlin", numberOfSearch = 189L, id = 87),
                createSearchKeyword(name = "kotlinx serialization", numberOfSearch = 177L, id = 32),
                createSearchKeyword(name = "kotest style guide", numberOfSearch = 150L, id = 44),
                createSearchKeyword(name = "젯브레인", numberOfSearch = 148L, id = 22),
                createSearchKeyword(name = "틀린코", numberOfSearch = 133L, id = 123),
                createSearchKeyword(name = "안드로이드프로그래밍", numberOfSearch = 87L, id = 43),
                createSearchKeyword(name = "webflux", numberOfSearch = 66L, id = 84),
                createSearchKeyword(name = "kotlin ktor", numberOfSearch = 23L, id = 145)
            )
        )
        every { searchService.getSearchKeywordsTop10() } returns response

        mockMvc.get("/api/search/hot-keywords")
            .andExpect {
                status { isOk() }
                content { response }
            }.andDo {
                handle(
                    document(
                        "get-hot-keywords",
                        responseFields(
                            fieldWithPath("keywords").type(JsonFieldType.ARRAY).description("키워드 목록"),
                            fieldWithPath("keywords[].id").type(JsonFieldType.NUMBER).description("키워드 ID"),
                            fieldWithPath("keywords[].name").type(JsonFieldType.STRING).description("키워드"),
                            fieldWithPath("keywords[].numberOfSearch").type(JsonFieldType.NUMBER).description("검색한 수")
                        )
                    )
                )
            }
    }
}
