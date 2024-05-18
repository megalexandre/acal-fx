package br.org.acal.application.controller.customer

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.TextField
import net.rgielen.fxweaver.core.FxmlView
import org.springframework.stereotype.Component

@Component
@FxmlView
class CustomerViewController: Initializable {

    lateinit var name: TextField

    lateinit var back: Button

    override fun initialize(location: URL?, resources: ResourceBundle?) {

    }

    fun startData(id: String?) {

    }


}