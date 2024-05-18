package br.org.acal.infrastructure.event

import br.org.acal.commons.enums.SceneUI
import org.springframework.context.ApplicationEvent

class MenuSelectedEvent(val ui: SceneUI, val id: String? = null) : ApplicationEvent(ui)