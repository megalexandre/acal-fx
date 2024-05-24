package br.org.acal.application.controller.customer

import br.org.acal.application.controller.customer.input.CustomerPageFilter
import br.org.acal.application.controller.customer.output.CustomerTableView
import br.org.acal.application.controller.customer.output.dataResume
import br.org.acal.application.controller.customer.output.toCustomerTableViewList
import br.org.acal.application.controller.customer.output.totalResume
import br.org.acal.application.render.StripedTableRender
import br.org.acal.commons.enums.SceneUI.CUSTOMER
import br.org.acal.commons.extensions.EventHandler
import br.org.acal.commons.extensions.avoidEmptyToNull
import br.org.acal.core.entity.CustomerFilter
import br.org.acal.core.entity.LimitOffsetAndSort
import br.org.acal.core.usecase.customer.CustomerPaginateUsecase
import br.org.acal.core.usecase.screen.ChangeScreenUsecase
import br.org.acal.infrastructure.event.MenuSelectedEvent
import java.net.URL
import java.util.ResourceBundle
import javafx.collections.FXCollections.observableArrayList
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.Pagination
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.control.cell.PropertyValueFactory
import net.rgielen.fxweaver.core.FxmlView
import org.springframework.data.domain.Sort.Direction.ASC
import org.springframework.stereotype.Component

@Component
@FxmlView
class CustomerSearchController(
    private val changeScreen: ChangeScreenUsecase,
    private val paginate: CustomerPaginateUsecase,
): Initializable {

    lateinit var searchTypeValue: TextField

    lateinit var searchNameValue: TextField

    lateinit var searchDocumentNumberValue: TextField

    lateinit var dataResume: Label

    lateinit var table: TableView<CustomerTableView>

    lateinit var search: Button

    lateinit var clear: Button

    lateinit var add: Button

    lateinit var pagination: Pagination

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        initializeTable()
        createActions()
        pagination.isVisible = false
    }

    private fun initializeTable() {
        table.columns.clear()
        table.isEditable = true
        table.rowFactory = StripedTableRender()
        table.columnResizePolicy = TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN

        addColumn("name", "Nome:")
        addColumn("type", "Tipo:")
        addColumn("documentNumber", "Identificador:")

        dataResume.text = ""

        pagination.currentPageIndexProperty().addListener{ _, _, offset ->
            find(createFilter().copy(
                limitOffsetAndSort = LimitOffsetAndSort(offset = offset)
                )
            )
        }

        table.setOnMouseClicked { event ->
            if (event.clickCount == 2) {
                val selectedItem = table.selectionModel.selectedItem
                selectedItem?.let { onClickItem(it) }
            }
        }
    }

    private fun addColumn(name: String, title: String) {
        val tableColumn = TableColumn<CustomerTableView, String>()
        tableColumn.text = title
        tableColumn.cellValueFactory = PropertyValueFactory(name)
        tableColumn.isResizable = true
        table.columns.add(tableColumn)
    }

    private fun createActions(){
        search.setOnAction {
            find()
        }

        clear.setOnAction {
            clear()
        }

        add.setOnAction {
            changeScreen.execute(MenuSelectedEvent(CUSTOMER))
        }

        addEvents(searchTypeValue)
        addEvents(searchNameValue)
        addEvents(searchDocumentNumberValue)
    }

    private fun addEvents(textField: TextField){
        textField.onKeyPressed = EventHandler.setEnterEvent {
            find()
        }
    }

    private fun clear() {
        table.items = observableArrayList()
        dataResume.text = ""
        searchTypeValue.text = ""
        searchNameValue.text = ""
        searchDocumentNumberValue.text = ""
        pagination.currentPageIndex = 0
        pagination.isVisible = false
    }

    private fun createFilter() =
        CustomerPageFilter().copy(
            filter = CustomerFilter(
                name = getName() ,
                type = getType(),
                documentNumber = getDocumentNumber(),
            ),
            limitOffsetAndSort = LimitOffsetAndSort().copy(
                direction = ASC,
                field = "name",
            )
        )

    private fun find(pageRequest: CustomerPageFilter = createFilter()) {

        val page = paginate.execute(pageRequest)
        val data = observableArrayList(
            page.content.map { it.toCustomerTableViewList() }
        )

        dataResume.text = "${page.dataResume()} ${page.totalResume()}"
        pagination.pageCount = page.totalPages
        table.items = data
        table.isVisible = true
        pagination.isVisible = true
    }

    private fun onClickItem(customer: CustomerTableView) {
        changeScreen.execute(MenuSelectedEvent(CUSTOMER, customer.id))
    }

    private fun getName(): String? = searchNameValue.text?.avoidEmptyToNull()
    private fun getType(): String? = searchTypeValue.text?.avoidEmptyToNull()
    private fun getDocumentNumber(): String? = searchDocumentNumberValue.text?.avoidEmptyToNull()
}