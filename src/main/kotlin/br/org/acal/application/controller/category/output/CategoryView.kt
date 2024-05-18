package br.org.acal.application.controller.category.output

import br.org.acal.commons.extensions.toCurrency
import br.org.acal.core.entity.Category
import br.org.acal.core.entity.CategoryValues

class CategoryView (
    val id: String,
    val name: String,
    val type: String,
    val values: List<CategoryValuesView>,
    val total: String,
    val water: String,
    val partner: String,
)

fun Category.toCategoryView() = CategoryView(
    id = this.id,
    name = this.name,
    type = this.type.value,
    values = this.values.map { CategoryValuesView.of(it) },
    total = this.total.toCurrency().replace("R$", "").trim(),
    water = this.water.toCurrency().replace("R$", "").trim(),
    partner = this.partner.toCurrency().replace("R$", "").trim(),
)

data class CategoryValuesView(
    val name: String,
    val value: String,
){
    companion object{
        fun of(categoryValues: CategoryValues) = CategoryValuesView(
            name = categoryValues.name,
            value = categoryValues.value.toCurrency().replace("R$", "").trim()
        )
    }

}

