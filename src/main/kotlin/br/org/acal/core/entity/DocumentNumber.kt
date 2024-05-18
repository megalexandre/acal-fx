package br.org.acal.core.entity

import br.org.acal.commons.enums.PersonType
import br.org.acal.commons.enums.PersonType.INDIVIDUAL
import br.org.acal.commons.enums.PersonType.LEGAL
import br.org.acal.commons.enums.PersonType.UNKNOWN

data class DocumentNumber(
    val number: String
){
    companion object{
        private const val MAXIMUM_LOG_SIZE = 3
        private const val CPF_SIZE = 11
        private const val CNPJ_SIZE = 14
    }

    val type: PersonType = when(number.length){
        CPF_SIZE -> INDIVIDUAL
        CNPJ_SIZE -> LEGAL
        else -> UNKNOWN
    }

    val isValid: Boolean = when(type){
        INDIVIDUAL -> CPF(number).valid
        LEGAL -> CNPJ(number).valid
        UNKNOWN -> false
    }

    val isInvalid: Boolean = !isValid

    val masked: String = when(number.length){
        CPF_SIZE -> CPF(number).masked
        CNPJ_SIZE -> CNPJ(number).masked
        else -> number
    }
    override fun toString(): String = when(number.length >= MAXIMUM_LOG_SIZE){
        true -> number.substring(0, MAXIMUM_LOG_SIZE) + "*".repeat(number.length - 3)
        false -> number
    }
}
