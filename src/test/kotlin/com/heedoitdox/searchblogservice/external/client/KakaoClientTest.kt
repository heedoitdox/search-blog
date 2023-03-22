package com.heedoitdox.searchblogservice.external.client

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.heedoitdox.searchblogservice.config.WireMockServerConfig
import com.heedoitdox.searchblogservice.external.createKakaoSearchBlogRequest
import com.heedoitdox.searchblogservice.external.feign.client.KakaoClient
import com.heedoitdox.searchblogservice.external.feign.client.KakaoSearchBlogResponse
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import java.io.FileReader

@SpringBootTest(classes = [WireMockServerConfig::class])
class KakaoClientTest(
    private val wireMockServer: WireMockServer,
    private val kakaoClient: KakaoClient
) : StringSpec({
    "카카오 블로그 검색 API 가 정상적으로 조회된다." {
        val request = createKakaoSearchBlogRequest()

        wireMockServer.stubGetPosts(
            WireMock.aResponse()
                .withStatus(HttpStatus.OK.value())
                .withBodyFile("response.json")
        )

        val actual = kakaoClient.searchBlog(request)
        val expected: KakaoSearchBlogResponse = Gson().fromJson(
            JsonReader(FileReader("src/test/resources/openfeign/jsonFiles/response.json")),
            KakaoSearchBlogResponse::class.java
        )
        actual shouldBe expected
    }
})

private fun WireMockServer.stubGetPosts(responseDefinitionBuilder: ResponseDefinitionBuilder) {
    stubFor(
        WireMock.get(WireMock.urlPathMatching("/v2/search/blog"))
            .willReturn(responseDefinitionBuilder)
    )
}
