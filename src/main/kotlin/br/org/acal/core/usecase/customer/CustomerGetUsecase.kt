package br.org.acal.core.usecase.customer

import br.org.acal.core.datasource.CustomerDataSource
import br.org.acal.core.entity.Customer
import br.org.acal.core.usecase.Usecase
import br.org.acal.infrastructure.exception.InvalidUsecaseException
import org.springframework.stereotype.Service


@Service
class CustomerGetUsecase(
    private val dataSource: CustomerDataSource
) : Usecase<String, Customer> {

    override fun execute(input: String): Customer =
        dataSource.findById(input) ?: throw InvalidUsecaseException("does not exists an customer with id: $input")

}
