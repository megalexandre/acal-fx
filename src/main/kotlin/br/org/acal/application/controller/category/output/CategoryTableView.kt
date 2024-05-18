package br.org.acal.application.controller.category.output

import br.org.acal.commons.extensions.toCurrency
import br.org.acal.core.entity.Category

data class CategoryTableView(
    val id: String,
    val name: String,
    val type: String,
    val water: String,
    val partner: String,
    val total: String
)
fun Category.toCustomerTableViewList(): CategoryTableView =
    CategoryTableView(
        id = this.id,
        name = this.name,
        type = this.type.value,
        partner = "R$ " + this.partner.toCurrency(),
        water = "R$ " + this.water.toCurrency(),
        total = "R$ " + this.total.toCurrency(),
    )

fun Collection<Category>.toCategoryTableView(): Collection<CategoryTableView> =
    this.map { it.toCustomerTableViewList() }
