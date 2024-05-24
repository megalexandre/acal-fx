package br.org.acal.application.controller.customer.input

import br.org.acal.core.entity.CustomerFilter
import br.org.acal.core.entity.LimitOffsetAndSort
import br.org.acal.core.entity.PageFilter
import org.springframework.data.domain.Sort.Direction.ASC

data class CustomerPageFilter(

    override val filter: CustomerFilter =
        CustomerFilter(
            id =  null,
            name =  null,
            type = null,
            documentNumber =  null,
        ),

    override val limitOffsetAndSort: LimitOffsetAndSort = LimitOffsetAndSort(
        offset = 0,
        size = 30,
        field = "name",
        direction = ASC
    )

): PageFilter(limitOffsetAndSort)

