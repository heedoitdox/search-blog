package com.heedoitdox.searchblogservice.api

import com.heedoitdox.searchblogservice.application.SearchKeywordResponse
import com.heedoitdox.searchblogservice.application.SearchRequest
import com.heedoitdox.searchblogservice.application.SearchResponse
import com.heedoitdox.searchblogservice.application.SearchService
import com.heedoitdox.searchblogservice.exception.RequestParamBindException
import com.heedoitdox.searchblogservice.exception.SearchError.INVALID_PARAMETER
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/search")
class SearchController(
    private val searchBlogService: SearchService
) {

    @GetMapping("/blog")
    fun search(
        @Valid @ModelAttribute
        request: SearchRequest,
        result: BindingResult
    ): ResponseEntity<Page<SearchResponse>> {
        if (result.hasErrors()) {
            throw RequestParamBindException(INVALID_PARAMETER, result.fieldErrors)
        }
        val response = searchBlogService.search(request)

        return ResponseEntity.ok().body(response)
    }

    @GetMapping("/hot-keywords")
    fun getSearchKeywordsTop10(): ResponseEntity<SearchKeywordResponse> {
        val response = searchBlogService.getSearchKeywordsTop10()

        return ResponseEntity.ok().body(response)
    }
}
