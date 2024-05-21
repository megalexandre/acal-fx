package br.org.acal.application.component

import java.text.NumberFormat
import java.text.ParseException
import javafx.scene.control.TextField
import javafx.scene.input.KeyEvent

class MonetaryField: TextField() {

    private val currencyFormat = NumberFormat.getCurrencyInstance()

    init {
        text = currencyFormat.format(0)

        addEventFilter(KeyEvent.KEY_TYPED) { event ->
            if (!isValidCharacter(event.character)) {
                event.consume()
            }
        }

        focusedProperty().addListener { _, _, newValue ->
            if (!newValue) {
                formatText()
            }
        }
    }

    private fun isValidCharacter(char: String): Boolean {
        return char.isEmpty() || char.matches(Regex("[0-9,.-]"))
    }

    private fun formatText() {
        text = try {
            val plainText = text.replace("[R$,.]".toRegex(), "").trim()
            val value = currencyFormat.parse(plainText)?.toDouble() ?: 0.0
            currencyFormat.format(value)
        } catch (e: ParseException) {
            currencyFormat.format(0)
        }
    }

    fun getMonetaryValue(): Double {
        val plainText = text.replace("[R$,.]".toRegex(), "").trim()
        return try {
            currencyFormat.parse(plainText)?.toDouble() ?: 0.0
        } catch (e: ParseException) {
            0.0
        }
    }

    fun setMonetaryValue(value: Double) {
        text = currencyFormat.format(value)
    }
}