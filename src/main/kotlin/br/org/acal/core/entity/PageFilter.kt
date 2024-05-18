package br.org.acal.core.entity

import br.org.acal.commons.enums.Direction

open class PageFilter (
    open val limitOffsetAndSort: LimitOffsetAndSort? = null,
    open val sortField: SortField? = null,
    open val filter: DefaultFilter? = null,
)

interface DefaultFilter

class LimitOffsetAndSort (
    val offset: Int? = 0,
    val size: Int? = 10,
    val field: String = "id",
    val direction: Direction = Direction.ASC,
)

class SortField (
    val field: String?,
    val direction: Direction?,
)
