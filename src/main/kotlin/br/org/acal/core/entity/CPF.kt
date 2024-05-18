package br.org.acal.core.entity

data class CPF(
    val number: String
){
    companion object{
        private const val MAXIMUM_SIZE = 3
        private const val CPF_SIZE = 11
        private const val CPF_REGEX = "[^0-9]"
        private const val EMPTY = ""
    }

    val valid: Boolean
        get() = this.validCPF()
    val masked: String
        get() = with(number){
            when (length == CPF_SIZE) {
                true -> "${substring(0, 3)}.${substring(3, 6)}.${substring(6, 9)}-${substring(9, 11)}"
                else -> this
            }
        }

    private fun validCPF(): Boolean {

        val clean: String = number.replace(CPF_REGEX.toRegex(), EMPTY).also {

            if (it.length != CPF_SIZE) {
                return false
            }

            if (it.all { i ->  i == it[0] }) {
                return false
            }
        }

        val digit1 = calculateDigit(clean.substring(0, 9))
        val digit2 = calculateDigit(clean.substring(0, 9) + digit1)

        return clean.endsWith("$digit1$digit2")
    }

    private fun calculateDigit(partialCpf: String): Int {
        val digit = partialCpf.indices.sumOf { partialCpf[it].toString().toInt() * (partialCpf.length + 1 - it) } % 11
        return if (digit < 2) 0 else 11 - digit
    }

    override fun toString(): String = when(number.length > MAXIMUM_SIZE){
        true ->  number.substring(0, MAXIMUM_SIZE) + "*".repeat(number.length - MAXIMUM_SIZE)
        false -> number
    }
}
