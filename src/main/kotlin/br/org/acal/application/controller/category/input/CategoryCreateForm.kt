package br.org.acal.application.controller.category.input

import br.org.acal.commons.enums.CategoryType
import br.org.acal.commons.extensions.toCurrency
import br.org.acal.core.entity.Category
import br.org.acal.core.entity.CategoryValues
import io.azam.ulidj.ULID
import jakarta.validation.Validator
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import org.springframework.validation.annotation.Validated


@Validated
class CategoryCreateForm(

    @field:NotEmpty(message = "Nome é um campo obrigatorio")
    @field:NotNull(message = "Nome é um campo obrigatório")
    val name: String?,

    @field:NotNull(message = "Categoria é um campo obrigatório", )
    val categoryType: String?,

    @field:NotEmpty(message = "Valor é um campo obrigatorio")
    @field:NotNull(message = "Valor do sócio é um campo obrigatório")
    val partnerValue: String?,

    @field:NotEmpty(message = "Valor é um campo obrigatorio")
    @field:NotNull(message = "Valor da água é um campo obrigatório")
    val waterValue: String?,

    val validator: Validator
){

    fun isValid(): Boolean =
        validator.validate(this).isEmpty()

    fun violations(): String =
        validator.validate(this).joinToString(separator = "\n") { "* ${it.message}."}

    fun createCategory(id: String? = null ): Category =
        Category(
            id = id ?: ULID.random(),
            name = name!!,
            type =  CategoryType.of(categoryType)!!,
            values =
            listOf(
                CategoryValues(
                    name = "Water",
                    value = BigDecimal(waterValue?.toCurrency())
                ),
                CategoryValues(
                    name = "Partnership",
                    value =  BigDecimal(partnerValue?.toCurrency())
                )
            ),
        )
}