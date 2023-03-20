package com.heedoitdox.searchblogservice.api

import com.heedoitdox.searchblogservice.application.SearchBlogRequest
import com.heedoitdox.searchblogservice.application.SearchBlogService
import com.heedoitdox.searchblogservice.external.client.KakaoSearchBlogResponse
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/search")
class SearchBlogController(
    private val searchBlogService: SearchBlogService
) {

    @GetMapping("/blog")
    fun search(
        @ModelAttribute request: SearchBlogRequest
    ): ResponseEntity<Page<KakaoSearchBlogResponse.Document>> {
        return ResponseEntity.ok().body(searchBlogService.search(request))
    }
}
