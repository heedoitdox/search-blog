package com.heedoitdox.searchblogservice.domain

import RepositoryTest
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

@RepositoryTest
class SearchKeywordRepositoryTest(
    private val searchKeywordRepository: SearchKeywordRepository
) : ExpectSpec({
    extensions(SpringTestExtension(SpringTestLifecycleMode.Root))

    context("키워드 조회") {
        searchKeywordRepository.saveAll(
            listOf(
                createSearchKeyword(name = "떡볶이", numberOfSearch = 1L),
                createSearchKeyword(name = "순대", numberOfSearch = 2L),
                createSearchKeyword(name = "튀김", numberOfSearch = 3L),
                createSearchKeyword(name = "세꼬시", numberOfSearch = 4L),
                createSearchKeyword(name = "낚지볶음", numberOfSearch = 5L),
                createSearchKeyword(name = "떡만두국", numberOfSearch = 6L),
                createSearchKeyword(name = "불닭볶음면", numberOfSearch = 7L),
                createSearchKeyword(name = "연어초밥", numberOfSearch = 8L),
                createSearchKeyword(name = "까르보나라떡볶이", numberOfSearch = 9L),
                createSearchKeyword(name = "도미노피자", numberOfSearch = 10L),
                createSearchKeyword(name = "크라잉버거", numberOfSearch = 11L)
            )
        )
        expect("이름으로 키워드를 조회한다.") {
            val actual = searchKeywordRepository.findByName("떡볶이")!!

            actual.name shouldBe "떡볶이"
        }

        expect("검색 카운트(numbner_of_search) 순으로 정렬된 10개의 키워드를 조회한다.") {
            val actual = searchKeywordRepository.findTop10ByOrderByNumberOfSearchDesc()

            actual shouldHaveSize 10
            actual.first().name shouldBe "크라잉버거"
            actual.last().name shouldBe "순대"
        }
    }
})
