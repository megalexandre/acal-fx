package br.org.acal.resources.gateway.category.data.input

import br.org.acal.commons.enums.CategoryType
import br.org.acal.core.entity.Category
import br.org.acal.core.entity.CategoryValues
import java.math.BigDecimal


data class CategoryPaginateGatewayResponse(
    val id: String,
    val name: String,
    val type: String,
    val values: Collection<CategoryValuesResponse>,
){
    fun toCategory(): Category = Category(
        id = id,
        name = name,
        type = CategoryType.of(type)!!,
        values = values.map { CategoryValues(it.name, BigDecimal(it.value)) }
    )
}
data class CategoryValuesResponse(
    val name: String,
    val value: String,
)


