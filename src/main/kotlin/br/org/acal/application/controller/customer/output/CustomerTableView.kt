package br.org.acal.application.controller.customer.output

import br.org.acal.core.entity.Customer
import org.springframework.data.domain.Page

data class CustomerTableView(
    val id: String,
    val name: String,
    val documentNumber: String,
    val type: String,
)

fun Customer.toCustomerTableViewList(): CustomerTableView =
    CustomerTableView(
        id = this.id,
        name = this.name,
        documentNumber = this.documentNumber.masked,
        type = this.type.value,
    )

fun Page<*>.dataResume(): String =
    "Exibindo de ${this.number + 1} a ${this.size} de ${this.totalElements} registros"

fun Page<*>.totalResume(): String =
    "distribuidos em ${this.totalPages} paginas"