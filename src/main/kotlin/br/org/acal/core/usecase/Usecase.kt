package br.org.acal.core.usecase


fun interface Usecase<in Input, out Output> {
    fun execute(input: Input): Output

}