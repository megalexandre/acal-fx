package br.org.acal.application.controller

import br.org.acal.application.controller.category.CategoryViewController
import br.org.acal.commons.enums.SceneUI
import br.org.acal.commons.enums.SceneUI.CATEGORY
import br.org.acal.commons.enums.SceneUI.CATEGORY_SEARCH
import br.org.acal.commons.enums.SceneUI.CUSTOMER_SEARCH
import br.org.acal.infrastructure.event.MenuSelectedEvent
import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.Initializable
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.MenuItem
import javafx.scene.layout.StackPane
import net.rgielen.fxweaver.core.FxWeaver
import net.rgielen.fxweaver.core.FxmlView
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
@FxmlView
class DashboardController(
    private val fxWeaver: FxWeaver,
    private val publisher: ApplicationEventPublisher
) : Initializable {

    lateinit var registrationInvoice: MenuItem

    lateinit var registrationPartner: MenuItem

    lateinit var registrationCategory: MenuItem

    lateinit var searchInvoice: MenuItem

    lateinit var contentArea: StackPane

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        registrationPartner.setOnAction {
            changeContentArea(CUSTOMER_SEARCH)
        }

        registrationCategory.setOnAction {
            changeContentArea(CATEGORY_SEARCH)
        }

    }

    @EventListener
    fun handleContextStart(event: MenuSelectedEvent) {
        changeContentArea(event.ui, event.id)
    }

    private fun changeContentArea(sceneUI: SceneUI, id: String? = null){
        contentArea.children.clear()

        val view: Node = fxWeaver.loadView(sceneUI.clazz, sceneUI.value)
        if(sceneUI == CATEGORY){
            id?.let {
                publisher.publishEvent(id)
            }
        }

        contentArea.children.add(view)
    }

}
