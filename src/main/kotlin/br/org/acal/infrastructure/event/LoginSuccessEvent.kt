package br.org.acal.infrastructure.event

import br.org.acal.commons.enums.SceneUI
import org.springframework.context.ApplicationEvent

class LoginSuccessEvent(ui: SceneUI) : ApplicationEvent(ui)