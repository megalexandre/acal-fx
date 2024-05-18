package br.org.acal.commons.extensions

fun String?.toCurrency(): String =
    this?.replace("R$", "")
        ?.replace(".","")
        ?.replace(",",".")
        ?.trim()
        ?: "0"
