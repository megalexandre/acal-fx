package br.org.acal

import javafx.stage.Stage
import org.springframework.context.ApplicationEvent

data class StageReadyEvent(val stage: Stage?) : ApplicationEvent(stage!!)