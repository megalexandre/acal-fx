package br.org.acal.infrastructure

import javafx.application.Application
import net.rgielen.fxweaver.core.FxWeaver
import net.rgielen.fxweaver.spring.SpringFxWeaver
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["br.org.acal.*"])
@EnableFeignClients(basePackages = ["br.org.acal.*"])
class AcalFxApplication{

	@Bean
	fun fxWeaver(applicationContext: ConfigurableApplicationContext?): FxWeaver {
		return SpringFxWeaver(applicationContext)
	}

}

fun main(args: Array<String>) {
	Application.launch(JavaFxApplication::class.java, *args)
}


