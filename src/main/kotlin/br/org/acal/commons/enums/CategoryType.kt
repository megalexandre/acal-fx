package br.org.acal.commons.enums

enum class CategoryType(val value: String) {

    FOUNDING("Sócio Fundador"),
    EFFECTIVE("Sócio Efetivo"),
    TEMPORARY("Temporário");
    companion object {
        fun of(value: String?): CategoryType? = entries.firstOrNull{ it.value == value }
    }
}
