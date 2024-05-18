package br.org.acal.application.controller.category

import br.org.acal.application.controller.category.output.CategoryTableView
import br.org.acal.application.controller.category.output.toCustomerTableViewList
import br.org.acal.application.render.StripedTableRender
import br.org.acal.commons.enums.SceneUI.CATEGORY
import br.org.acal.core.entity.PageFilter
import br.org.acal.core.usecase.category.CategoryPaginateUsecase
import br.org.acal.infrastructure.event.MenuSelectedEvent
import java.net.URL
import java.util.ResourceBundle
import javafx.collections.FXCollections
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.control.cell.PropertyValueFactory
import net.rgielen.fxweaver.core.FxmlView
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component


@Component
@FxmlView
class CategorySearchController(
    private val publisher: ApplicationEventPublisher,
    private val paginate: CategoryPaginateUsecase
): Initializable {

    lateinit var searchTotal: TextField

    lateinit var searchType: TextField

    lateinit var searchName: TextField

    lateinit var add: Button

    lateinit var table: TableView<CategoryTableView>

    lateinit var search: Button

    lateinit var clear: Button

    lateinit var searchText: TextField

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        initializeTable()

        search.setOnAction {
            find()
        }

        searchText.setOnAction {
            find()
        }

        clear.setOnAction {
            clear()
        }

        add.setOnAction {
            publisher.publishEvent(MenuSelectedEvent(CATEGORY))
        }
    }

    private fun clear() {
        this.table.items = null
        this.searchText.text = null
        this.searchTotal.text = null
        this.searchType.text = null
        this.searchName.text = null
    }

    private fun find() {

        val page = paginate.execute(PageFilter())

        val data = FXCollections.observableArrayList(
            page.content.map { it.toCustomerTableViewList() }
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
        publisher.publishEvent(MenuSelectedEvent(CATEGORY, category.id))
    }
    private fun addColumn(name: String, title: String) {
        val tableColumn = TableColumn<CategoryTableView, String>()
        tableColumn.text = title
        tableColumn.cellValueFactory = PropertyValueFactory(name)
        tableColumn.isResizable = true
        table.columns.add(tableColumn)
    }

}