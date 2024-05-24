package br.org.acal.core.usecase.screen

import br.org.acal.infrastructure.event.MenuSelectedEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class ChangeScreenUsecase(
    private val publisher: ApplicationEventPublisher,
) {

    fun execute(menuSelectedEvent: MenuSelectedEvent){
        publisher.publishEvent(menuSelectedEvent)
    }

}