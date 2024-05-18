package br.org.acal.core.usecase.login

import br.org.acal.core.entity.LoginAttempt
import br.org.acal.core.usecase.Usecase
import org.springframework.stereotype.Service

@Service
class LoginUsecase: Usecase<LoginAttempt, Boolean> {
    override fun execute(input: LoginAttempt): Boolean = true

}