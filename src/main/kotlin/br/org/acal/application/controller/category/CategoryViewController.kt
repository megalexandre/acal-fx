package br.org.acal.application.controller.category

import br.org.acal.application.controller.category.input.CategoryCreateForm
import br.org.acal.application.controller.category.output.toCategoryView
import br.org.acal.commons.enums.CategoryType
import br.org.acal.commons.enums.SceneUI.CATEGORY_SEARCH
import br.org.acal.core.usecase.category.CategoryDeleteUsecase
import br.org.acal.core.usecase.category.CategoryFindByIdUsecase
import br.org.acal.core.usecase.category.CategorySaveUsecase
import br.org.acal.core.usecase.category.CategoryUpdateUsecase
import br.org.acal.infrastructure.event.MenuSelectedEvent
import jakarta.validation.Validator
import java.net.URL
import java.util.ResourceBundle
import javafx.collections.FXCollections
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.FlowPane
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
    private val delete: CategoryDeleteUsecase,
    private val publisher: ApplicationEventPublisher,
    private val validator: Validator
) : Initializable {

    lateinit var flowPane: FlowPane
    lateinit var title: Label
    lateinit var confirm: Button
    lateinit var remove: Button
    lateinit var back: Button

    lateinit var name: TextField
    lateinit var partnerValue: TextField
    lateinit var waterValue: TextField
    lateinit var categories: ComboBox<String>
    lateinit var errorMessage: Label

    var id: String? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        id = null
        errorMessage.text = ""
        categories.items = FXCollections.observableArrayList(CategoryType.entries.map { it.value })

        back.setOnAction {
            publisher.publishEvent(MenuSelectedEvent(CATEGORY_SEARCH))
        }

        confirm.setOnAction {
            confirm()
        }

        remove.isVisible = false
        remove.setOnAction {
            delete.execute(id!!)
            publisher.publishEvent(MenuSelectedEvent(CATEGORY_SEARCH))
        }

    }

    private fun confirm(){

        createFormCategory().also {
            if (it.isValid()){
                when(id){
                    null -> save.execute(it.createCategory())
                    else -> update.execute(it.createCategory(id))
                }
                publisher.publishEvent(MenuSelectedEvent(CATEGORY_SEARCH))
            }else {
                errorMessage.text = it.violations()
            }
        }
    }

    private fun createFormCategory(): CategoryCreateForm =
        CategoryCreateForm(
            name = name.text,
            categoryType = categories.value,
            waterValue = waterValue.text,
            partnerValue = partnerValue.text,
            validator = validator
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

        remove.isVisible = true
    }



}