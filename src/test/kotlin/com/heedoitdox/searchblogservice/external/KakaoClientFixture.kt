package com.heedoitdox.searchblogservice.external

import com.heedoitdox.searchblogservice.external.feign.client.KakaoSearchBlogRequest

fun createKakaoSearchBlogRequest(
    query: String? = "딸기",
    page: Int? = 1,
    size: Int? = 10,
    sort: String? = "accuracy"
): KakaoSearchBlogRequest = KakaoSearchBlogRequest(query!!, page, size, sort)
