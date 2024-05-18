package br.org.acal.application.controller.customer

import br.org.acal.application.controller.customer.output.CustomerTableView
import br.org.acal.application.controller.customer.output.toCustomerTableViewList
import br.org.acal.commons.enums.SceneUI.CUSTOMER
import br.org.acal.core.usecase.customer.CustomerFindAllUsecase
import br.org.acal.infrastructure.event.MenuSelectedEvent
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.scene.control.Button
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import net.rgielen.fxweaver.core.FxmlView
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
@FxmlView
class CustomerSearchController(
    private val publisher: ApplicationEventPublisher,
    private val findAll: CustomerFindAllUsecase
) {

    lateinit var add: Button

    lateinit var table: TableView<CustomerTableView>

    lateinit var search: Button

    fun initialize() {
        initializeTable()
        search.setOnAction{
            find()
        }
    }

    private fun find(){
        val data = FXCollections.observableArrayList(
            findAll.execute(Unit).toCustomerTableViewList()
        )

        table.items = data
    }

    private fun initializeTable(){
        table.columns.clear()
        table.isEditable = true
        table.columnResizePolicy = TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN

        addColumn("name", "Nome:")
        addColumn("document", "Documento:")
        addColumn("type", "Tipo:")

        table.selectionModel.selectedItemProperty()
            .addListener { _: ObservableValue<out CustomerTableView?>?, _: CustomerTableView?, newValue: CustomerTableView? ->
                newValue?.let { onClickItem(it) }
            }

        add.setOnAction {
            publisher.publishEvent(MenuSelectedEvent(CUSTOMER))
        }

    }

    private fun onClickItem(customer: CustomerTableView) {
        publisher.publishEvent(MenuSelectedEvent(CUSTOMER, customer.id))
    }

    private fun addColumn(name: String, title: String){
        val tableColumn = TableColumn<CustomerTableView, String>()
        tableColumn.text = title
        tableColumn.cellValueFactory = PropertyValueFactory(name)
        tableColumn.isResizable = true
        table.columns.add(tableColumn)
    }
}