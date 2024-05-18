package br.org.acal.application.controller.category

import br.org.acal.application.controller.category.output.toCategoryView
import br.org.acal.commons.enums.CategoryType
import br.org.acal.commons.enums.SceneUI.CATEGORY_SEARCH
import br.org.acal.commons.extensions.toCurrency
import br.org.acal.core.entity.Category
import br.org.acal.core.entity.CategoryValues
import br.org.acal.core.usecase.category.CategoryFindByIdUsecase
import br.org.acal.core.usecase.category.CategorySaveUsecase
import br.org.acal.core.usecase.category.CategoryUpdateUsecase
import br.org.acal.infrastructure.event.MenuSelectedEvent
import io.azam.ulidj.ULID.random
import java.math.BigDecimal
import java.net.URL
import java.util.ResourceBundle
import javafx.collections.FXCollections
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import net.rgielen.fxweaver.core.FxmlView
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component


@Component
@FxmlView
class CategoryViewController(
    private val findById: CategoryFindByIdUsecase,
    private val save: CategorySaveUsecase,
    private val update: CategoryUpdateUsecase,
    private val publisher: ApplicationEventPublisher,
) : Initializable {

    lateinit var title: Label
    lateinit var confirm: Button
    lateinit var back: Button

    lateinit var name: TextField
    lateinit var partnerValue: TextField
    lateinit var waterValue: TextField
    lateinit var categories: ComboBox<String>
    var id: String? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        categories.items = FXCollections.observableArrayList(CategoryType.entries.map { it.value })

        back.setOnAction {
            publisher.publishEvent(MenuSelectedEvent(CATEGORY_SEARCH))
        }

        confirm.setOnAction {
            confirm()
        }
    }

    private fun confirm(){
        createCategory().also { category ->
            when(id){
                null -> save.execute(category)
                else -> update.execute(category)
            }
        }

        publisher.publishEvent(MenuSelectedEvent(CATEGORY_SEARCH))
    }
    private fun createCategory(): Category =
        Category(
            id = id ?: random(),
            name = name.text,
            type = CategoryType.of(categories.value),
            values =
            listOf(
                CategoryValues(
                    name = "Water",
                    value = BigDecimal(waterValue.text.toCurrency())
                ),
                CategoryValues(
                    name = "Partnership",
                    value =  BigDecimal(partnerValue.text.toCurrency())
                )
            ),
        )

    @EventListener
    fun loadData(id: String){
        this.id = id
        title.text = "Editar Categoria"
        findById.execute(id)?.toCategoryView()?.let {
            name.text = it.name
            partnerValue.text = it.partner
            waterValue.text = it.water
            categories.value= it.type
        }
    }



}