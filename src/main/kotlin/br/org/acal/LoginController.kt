package br.org.acal

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import net.rgielen.fxweaver.core.FxmlView
import org.springframework.stereotype.Component

@Component
@FxmlView
class LoginController : Initializable {

    @FXML
    lateinit var welcomeText: Label

    @FXML
    lateinit var inputName: TextField

    @FXML
    lateinit var inputPassword: PasswordField

    @FXML
    lateinit var confirmButton: Button

    @FXML
    override fun initialize(location: URL?, resources: ResourceBundle?) {

        welcomeText.style = """
            -fx-font-size: 24px; 
            -fx-font-family: 'Arial'; 
            -fx-font-weight: bold; 
        """

        inputName.setOnKeyPressed { event ->
            if (event.code == KeyCode.ENTER) {
                login()
            }
        }

        inputPassword.setOnKeyPressed { event ->
            if (event.code == KeyCode.ENTER) {
                login()
            }
        }

        confirmButton.setOnAction {
            login()
        }

    }

    private fun login(){
        welcomeText.text = "Bem-Vindo!"
        inputName.text = ""
        inputPassword.text = ""
    }

}