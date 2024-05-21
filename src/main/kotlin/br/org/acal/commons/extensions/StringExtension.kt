package br.org.acal.commons.extensions

fun String?.toCurrency(): String =
    when(this.isNullOrEmpty()){
        true -> "0"
        else -> this.replace("R$", "")
            .replace(".","")
            .replace(",",".")
            .trim()
    }

fun String.avoidEmptyToNull(): String? =
    when(this.isEmpty()){
        true -> null
        else -> this
    }

