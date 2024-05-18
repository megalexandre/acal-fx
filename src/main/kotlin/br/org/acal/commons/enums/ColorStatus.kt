package br.org.acal.commons.enums

enum class ColorStatus(val light: String, val dark: String) {
    SUCCESS("#dafbe1", "b6e8b2"),
    DANGER("#ffebe9", "#ffccc9"),
    WARNING("#fff8c5", "#e6d794"),
    INFO("#ddf4ff", "#c5c5c5"),
    NEUTRAL("#ffffff", "#f2f2f2")
}
