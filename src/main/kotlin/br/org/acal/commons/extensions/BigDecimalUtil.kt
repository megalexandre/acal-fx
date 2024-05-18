package br.org.acal.commons.extensions

import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import java.math.RoundingMode.HALF_DOWN
import java.text.DecimalFormat
import java.util.Locale

fun BigDecimal.toMonetary(): BigDecimal =
    when(this){
        ZERO -> ZERO
        else -> this.setScale(9, HALF_DOWN)
    }


fun BigDecimal.toCurrency(locale: Locale? = null): String =
    when(locale == null){
        true ->  DecimalFormat.getCurrencyInstance(Locale("pt", "BR")).format(this)
        else ->  DecimalFormat.getCurrencyInstance(locale).format(this)
    }

fun List<BigDecimal>?.sum(): BigDecimal =
    this?.fold(ZERO) { acc, expense -> acc + expense }
        ?.toMonetary()
        ?: ZERO
