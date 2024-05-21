package br.org.acal.core.entity

import br.org.acal.commons.enums.CategoryType
import br.org.acal.commons.extensions.sum
import br.org.acal.commons.extensions.toMonetary
import java.math.BigDecimal
import java.math.BigDecimal.ZERO

data class Category (
    val id: String,
    val name: String,
    val type: CategoryType,
    val values: Collection<CategoryValues>,
){
    val total: BigDecimal
        get() = values.map { it.value }.sum()

    val water: BigDecimal
        get() = values.firstOrNull {
            it.name.lowercase() == "Water".lowercase()
        }?.value ?: ZERO

    val partner: BigDecimal
        get() = values.firstOrNull {
            it.name.lowercase() == "Partner".lowercase() ||
            it.name.lowercase() == "partnership".lowercase()
        }?.value ?: ZERO

}
data class CategoryValues(
    val name: String,
    val value: BigDecimal,
)

data class CategoryFilter(
    val id: String? = null,
    val name: String? = null,
    val type: String? = null,
    val water: String? = null,
    val partner: String? = null,
) : DefaultFilter {
    val formattedWater: String?
        get() = when(water) {
            null -> null
            else -> BigDecimal(water).toMonetary().toString()
        }

    val formattedPartner: String?
        get() = when(partner) {
            null -> null
            else -> BigDecimal(partner).toMonetary().toString()
        }
}