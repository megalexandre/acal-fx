package br.org.acal.commons.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime?.toFormat(): String =
    this?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")) ?: ""
