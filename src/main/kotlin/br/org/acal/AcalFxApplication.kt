package br.org.acal

import javafx.application.Application
import net.rgielen.fxweaver.core.FxWeaver
import net.rgielen.fxweaver.spring.SpringFxWeaver
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean


@SpringBootApplication
class AcalFxApplication{
	@Bean
	fun fxWeaver(applicationContext: ConfigurableApplicationContext?): FxWeaver {
		return SpringFxWeaver(applicationContext)
	}
}

fun main(args: Array<String>) {
	Application.launch(JavaFxApplication::class.java, *args)
}


