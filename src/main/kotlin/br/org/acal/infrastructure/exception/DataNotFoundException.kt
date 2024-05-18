package br.org.acal.infrastructure.exception

class DataNotFoundException(override val message: String): RuntimeException(message)