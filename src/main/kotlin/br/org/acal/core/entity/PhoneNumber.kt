package br.org.acal.core.entity

data class PhoneNumber(
    val preferential: Boolean,
    val number: String,
    val isWhatApp: Boolean
)