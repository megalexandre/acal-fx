package br.org.acal.application.controller.customer.output

import br.org.acal.core.entity.Customer

data class CustomerTableView(
    val id: String,
    val name: String,
    val document: String,
    val type: String,
)

fun Customer.toCustomerTableViewList(): CustomerTableView =
    CustomerTableView(
        id = this.id,
        name = this.name,
        document = this.documentNumber.masked,
        type = this.type.value,
    )

fun Collection<Customer>.toCustomerTableViewList(): Collection<CustomerTableView> =
    this.map { it.toCustomerTableViewList() }