package br.org.acal.core.entity

import br.org.acal.commons.enums.PersonType
import java.time.LocalDate

data class Customer (
    val id: String,
    val name: String,
    val documentNumber: DocumentNumber,
    var birthDay: LocalDate? = null,
    val phoneNumbers: List<PhoneNumber>? = null,
    val active: Boolean,
){

    val type: PersonType = documentNumber.type
    val preferentialNumber = phoneNumbers?.first { it.preferential }?.number
}

data class CustomerFilter(
    val id: String? = null,
    val name: String? = null,
    val type: String? = null,
    val documentNumber: String? = null,
) : DefaultFilter