package com.heedoitdox.searchblogservice.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(name = "UK_SearchKeyword_Name", columnNames = ["name"])
    ],
    indexes = [Index(name = "IDX_SearchKeyword_Name", columnList = "name")]
)
class SearchKeyword(
    name: String,
    numberOfSearch: Long = DEFAULT_NUMBER_OF_SEARCH,
    id: Long = 0L
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = id

    @Column(name = "name", nullable = false)
    val name: String = name

    @Column(name = "number_of_search", nullable = false)
    var numberOfSearch: Long = numberOfSearch

    fun addOneNumberOfSearch() {
        this.numberOfSearch++
    }

    companion object {
        private const val DEFAULT_NUMBER_OF_SEARCH = 1L
    }
}
