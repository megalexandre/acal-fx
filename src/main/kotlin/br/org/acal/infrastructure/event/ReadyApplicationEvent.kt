package br.org.acal.infrastructure.event

import br.org.acal.commons.enums.SceneUI
import javafx.stage.Stage
import org.springframework.context.ApplicationEvent

data class ReadyApplicationEvent(val stage: Stage? = null, val ui: SceneUI? = null) : ApplicationEvent(stage!!)

