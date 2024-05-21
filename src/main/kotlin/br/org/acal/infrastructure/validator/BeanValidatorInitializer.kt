package br.org.acal.infrastructure.validator

import jakarta.validation.Validation
import jakarta.validation.Validator
import org.springframework.stereotype.Component

@Component
class BeanValidatorInitializer {
    fun validatorFactory(): Validator = Validation.buildDefaultValidatorFactory().validator

}