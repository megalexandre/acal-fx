package br.org.acal.resources.gateway.category.data.output

import br.org.acal.commons.enums.CategoryType
import br.org.acal.core.entity.Category
import br.org.acal.core.entity.CategoryValues

data class CategoryUpdateRequest (
    val id: String,
    val name: String,
    val type: CategoryType,
    val values: Collection<CategoryValues>,
)

fun Category.toCategoryUpdateRequest() = CategoryUpdateRequest(
    id = this.id,
    name = this.name,
    type = this.type,
    values = this.values
)

