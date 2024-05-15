package br.org.acal.application

import javafx.fxml.FXML
import javafx.scene.control.Label
import net.rgielen.fxweaver.core.FxmlView
import org.springframework.stereotype.Component

@Component
@FxmlView("MainController.fxml")
class MainController {
    private val greeting = "greeting"

    @FXML
    val label: Label? = null

}