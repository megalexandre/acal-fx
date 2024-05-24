package br.org.acal.core.entity

import org.springframework.data.domain.Sort


open class PageFilter (
    open val limitOffsetAndSort: LimitOffsetAndSort,
    open val filter: DefaultFilter? = null,
)

interface DefaultFilter

data class LimitOffsetAndSort (
    val offset: Number = 0,
    val size: Number = 30,
    val field: String = "id",
    val direction: Sort.Direction = Sort.Direction.ASC,
)
