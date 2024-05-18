package br.org.acal.infrastructure

import br.org.acal.application.controller.DashboardController
import br.org.acal.application.controller.LoginController
import br.org.acal.infrastructure.event.LoginSuccessEvent
import br.org.acal.infrastructure.event.ReadyApplicationEvent
import javafx.scene.Scene
import javafx.stage.Stage
import net.rgielen.fxweaver.core.FxWeaver
import org.springframework.context.ApplicationListener
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
@Component
class LoginInitializer (
    private val fxWeaver: FxWeaver
) : ApplicationListener<ReadyApplicationEvent> {

    var stage: Stage? = null

    @EventListener
    fun handleContextStart(event: LoginSuccessEvent) {
        showDashboard()
    }

    override fun onApplicationEvent(event: ReadyApplicationEvent) {
        event.stage?.apply {
            stage = this
            val scene = Scene(fxWeaver.loadView(LoginController::class.java))
            this.scene = scene
            setScene(scene)
            show()
        }
    }

    private fun showDashboard(){
        stage?.let {
            with(it){
                title = "Acal::Dashboard"
                scene = Scene(fxWeaver.loadView(DashboardController::class.java))
                isMaximized = true
                centerOnScreen()
            }
        }
    }

}