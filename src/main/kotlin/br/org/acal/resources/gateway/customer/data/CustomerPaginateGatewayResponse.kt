package br.org.acal.resources.gateway.customer.data

import br.org.acal.core.entity.Customer
import br.org.acal.core.entity.DocumentNumber
import br.org.acal.core.entity.PhoneNumber

data class CustomerPaginateGatewayResponse(
    val id: String,
    val name: String,
    val documentNumber: String,
    val phoneNumbers: List<PhoneNumber>? = null,
    val active: Boolean = true,
){
    fun toCustomer(): Customer = Customer(
        id = id,
        name = name,
        documentNumber = DocumentNumber(documentNumber),
        phoneNumbers = phoneNumbers,
        active = active,
    )

}

fun Collection<CustomerPaginateGatewayResponse>.toCustomer(): Collection<Customer> = this.map { it.toCustomer() }

