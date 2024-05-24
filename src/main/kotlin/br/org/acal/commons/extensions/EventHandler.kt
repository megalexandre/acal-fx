package br.org.acal.commons.extensions

import javafx.event.EventHandler
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent

class EventHandler {
    companion object{
        fun setEnterEvent(action: () -> Unit): EventHandler<KeyEvent> {
            return EventHandler { event ->
                if (event.code == KeyCode.ENTER) {
                    action()
                }
            }
        }
    }

}