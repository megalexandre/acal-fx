package br.org.acal

import br.org.acal.application.MainController
import javafx.scene.Scene
import net.rgielen.fxweaver.core.FxWeaver
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class PrimaryStageInitializer (
    private val fxWeaver: FxWeaver
) : ApplicationListener<StageReadyEvent> {
    override fun onApplicationEvent(event: StageReadyEvent) {
        event.stage?.apply {
            val scene = Scene(fxWeaver.loadView(MainController::class.java), 400.0, 300.0)
            setScene(scene)
            show()
        }
    }
}