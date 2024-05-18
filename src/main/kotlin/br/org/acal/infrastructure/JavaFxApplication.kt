package br.org.acal.infrastructure

import br.org.acal.infrastructure.event.ReadyApplicationEvent
import javafx.application.Application
import javafx.application.Platform
import javafx.stage.Stage
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ConfigurableApplicationContext


class JavaFxApplication: Application() {

    private var applicationContext: ConfigurableApplicationContext? = null

    override fun init() {
        val args = parameters.raw.toTypedArray<String>()

        applicationContext = SpringApplicationBuilder()
            .sources(AcalFxApplication::class.java)
            .run(*args)
    }

    override fun start(stage: Stage?) {
        setUserAgentStylesheet("/themes/primer-light.css")
        applicationContext?.publishEvent(ReadyApplicationEvent(stage))
    }

    override fun stop() {
        applicationContext!!.close()
        Platform.exit()
    }
}