package br.org.acal.infrastructure

import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun Logger.info(lambda: () -> String) {
    if (isInfoEnabled) {
        info(lambda())
    }
}

inline fun Logger.error(lambda: () -> String) {
    if (isInfoEnabled) {
        error(lambda())
    }
}



abstract class Sl4jLogger {

    protected val logger: Logger = LoggerFactory.getLogger(javaClass)

}
