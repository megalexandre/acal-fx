package br.org.acal.application.controller.category

import br.org.acal.application.component.ComboValues
import br.org.acal.application.controller.category.output.CategoryTableView
import br.org.acal.application.controller.category.output.toCategoryTableViewList
import br.org.acal.application.render.StripedTableRender
import br.org.acal.commons.enums.CategoryType
import br.org.acal.commons.enums.SceneUI.CATEGORY
import br.org.acal.commons.extensions.EventHandler
import br.org.acal.commons.extensions.avoidEmptyToNull
import br.org.acal.core.entity.CategoryFilter
import br.org.acal.core.usecase.category.CategoryFindUsecase
import br.org.acal.core.usecase.screen.ChangeScreenUsecase
import br.org.acal.infrastructure.event.MenuSelectedEvent
import java.net.URL
import java.util.ResourceBundle
import javafx.collections.FXCollections.observableArrayList
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.control.cell.PropertyValueFactory
import net.rgielen.fxweaver.core.FxmlView
import org.springframework.stereotype.Component


@Component
@FxmlView
class CategorySearchController(
    private val find: CategoryFindUsecase,
    private val changeScreen: ChangeScreenUsecase
): Initializable {

    lateinit var table: TableView<CategoryTableView>

    lateinit var searchName: TextField

    lateinit var searchType: ComboBox<ComboValues>

    lateinit var searchTotalValue: TextField

    lateinit var searchPartnerValue: TextField

    lateinit var searchWaterValue: TextField

    lateinit var search: Button

    lateinit var clear: Button

    lateinit var add: Button

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        initializeTable()

        search.setOnAction {
            find()
        }

        clear.setOnAction {
            clear()
        }

        add.setOnAction {
            changeScreen.execute(MenuSelectedEvent(CATEGORY))
        }

        searchName.onKeyPressed = EventHandler.setEnterEvent {
            find()
        }

        searchWaterValue.onKeyPressed = EventHandler.setEnterEvent {
            find()
        }
        searchPartnerValue.onKeyPressed = EventHandler.setEnterEvent {
            find()
        }

        searchTotalValue.onKeyPressed = EventHandler.setEnterEvent {
            find()
        }

        val select = ComboValues("Selecione", null)
        val values = observableArrayList(select).apply {
            addAll(CategoryType.entries.map { it.value }.map { ComboValues(it, it) })
        }

        searchType.items = values
        searchType.value = select

        searchType.setOnAction {
            find()
        }
    }


    private fun clear() {
        table.items = observableArrayList()

        searchName.text = ""
        searchType.value = searchType.items[0]
        searchTotalValue.text = ""
        searchPartnerValue.text = ""
        searchWaterValue.text = ""
        searchTotalValue.text = ""
    }

    private fun find() {

        val content = find.execute(
            CategoryFilter(
                id = null,
                name = searchName.text.avoidEmptyToNull(),
                type = searchType.value.value,
                water = searchWaterValue.text?.avoidEmptyToNull(),
                partner = searchPartnerValue.text?.avoidEmptyToNull()
            )
        )

        val data = observableArrayList(
            content.map { it.toCategoryTableViewList() }
        )

        table.items = data
        table.isVisible = true
    }

    private fun initializeTable() {
        table.columns.clear()
        table.isEditable = true
        table.rowFactory = StripedTableRender()
        table.columnResizePolicy = TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN

        addColumn("name", "Nome:")
        addColumn("type", "Tipo:")
        addColumn("water", "Água:")
        addColumn("partner", "Sócio:")
        addColumn("total", "Total:")

        table.setOnMouseClicked { event ->
            if (event.clickCount == 2) {
                val selectedItem = table.selectionModel.selectedItem
                selectedItem?.let { onClickItem(it) }
            }
        }

    }

    private fun onClickItem(category: CategoryTableView) {
        changeScreen.execute(MenuSelectedEvent(CATEGORY, category.id))
    }

    private fun addColumn(name: String, title: String) {
        val tableColumn = TableColumn<CategoryTableView, String>()
        tableColumn.text = title
        tableColumn.cellValueFactory = PropertyValueFactory(name)
        tableColumn.isResizable = true
        table.columns.add(tableColumn)
    }


}