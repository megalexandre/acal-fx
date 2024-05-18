package br.org.acal.commons.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.swing.text.DateFormatter

fun LocalDate?.toFormat(): String =
    this?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: ""
