package com.heedoitdox.searchblogservice.api

import com.heedoitdox.searchblogservice.application.SearchBlogRequest
import com.heedoitdox.searchblogservice.application.SearchBlogService
import com.heedoitdox.searchblogservice.exception.ErrorCode.INVALID_PARAMETER
import com.heedoitdox.searchblogservice.exception.RequestParamBindException
import com.heedoitdox.searchblogservice.external.client.KakaoSearchBlogResponse
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/search")
class SearchBlogController(
    private val searchBlogService: SearchBlogService
) {

    @GetMapping("/blog")
    fun search(
        @Valid @ModelAttribute
        request: SearchBlogRequest,
        result: BindingResult
    ): ResponseEntity<Page<KakaoSearchBlogResponse.Document>> {
        if (result.hasErrors()) {
            throw RequestParamBindException(INVALID_PARAMETER, result.fieldErrors)
        }
        val response = searchBlogService.search(request)

        return ResponseEntity.ok().body(response)
    }
}
