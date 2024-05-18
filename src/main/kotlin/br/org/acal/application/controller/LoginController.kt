package br.org.acal.application.controller

import br.org.acal.commons.enums.SceneUI.LOGIN
import br.org.acal.core.entity.LoginAttempt
import br.org.acal.core.usecase.login.LoginUsecase
import br.org.acal.infrastructure.event.LoginSuccessEvent
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
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
@FxmlView
class LoginController(
    val login: LoginUsecase,
    val publisher: ApplicationEventPublisher
) : Initializable {

    @FXML
    lateinit var welcomeText: Label

    lateinit var message: Label

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

        message.text = ""
    }

    private fun login(){
        login.execute(
            LoginAttempt(
            username = inputName.text ,
            password = inputPassword.text,
        )
        ).also {
             when(it){
                 true -> loginAttemptSuccess()
                 false -> loginAttemptError()
             }
        }
    }

    private fun loginAttemptSuccess(){
        welcomeText.text = "Bem-vindo! ${inputName.text}"
        publisher.publishEvent(LoginSuccessEvent( ui = LOGIN))
    }

    private fun loginAttemptError(){
        welcomeText.text = "Bem-vindo!"
        inputName.text = ""
        inputPassword.text = ""
        message.text = "Nome e/ou senha incorretos."
    }

}