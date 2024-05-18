package br.org.acal.core.usecase.customer

import br.org.acal.core.datasource.CustomerDataSource
import br.org.acal.core.entity.Customer
import br.org.acal.core.usecase.Usecase
import org.springframework.stereotype.Service


@Service
class CustomerFindAllUsecase(
    private val dataSource: CustomerDataSource
) : Usecase<Unit, Collection<Customer>> {

    override fun execute(input: Unit): Collection<Customer> = dataSource.findAll()

}
