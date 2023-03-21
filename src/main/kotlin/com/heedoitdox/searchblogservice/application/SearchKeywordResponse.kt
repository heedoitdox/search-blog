package com.heedoitdox.searchblogservice.application

import com.heedoitdox.searchblogservice.domain.SearchKeyword

data class SearchKeywordResponse(
    val keywords: List<SearchKeywordItem>
) {
    data class SearchKeywordItem(
        val id: Long,
        val name: String,
        val numberOfSearch: Long
    ) {
        companion object {
            fun from(searchKeyword: SearchKeyword): SearchKeywordItem = with(searchKeyword) {
                SearchKeywordItem(id, name, numberOfSearch)
            }
        }
    }
    companion object {
        fun from(list: List<SearchKeyword>): SearchKeywordResponse =
            SearchKeywordResponse(list.map(SearchKeywordItem::from))
    }
}
