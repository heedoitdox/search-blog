package com.heedoitdox.searchblogservice.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import javax.persistence.LockModeType

interface SearchKeywordRepository : JpaRepository<SearchKeyword, Long> {
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    fun findByName(name: String): SearchKeyword?

    fun findTop10ByOrderByNumberOfSearchDesc(): List<SearchKeyword>
}
