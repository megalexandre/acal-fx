package br.org.acal

import javafx.application.Application
import javafx.application.Platform
import javafx.stage.Stage
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ConfigurableApplicationContext


class JavaFxApplication: Application() {

    private var applicationContext: ConfigurableApplicationContext? = null

    override fun init() {
        applicationContext = SpringApplicationBuilder()
            .sources(AcalFxApplication::class.java)
            .run(*parameters.raw.toTypedArray<String>())
    }

    override fun start(stage: Stage?) {
        applicationContext?.publishEvent(StageReadyEvent(stage))
    }

    override fun stop() {
        applicationContext!!.close()
        Platform.exit()
    }
}