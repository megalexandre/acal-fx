package br.org.acal.core.usecase.customer

import br.org.acal.core.datasource.CustomerDataSource
import br.org.acal.core.entity.Customer
import br.org.acal.core.usecase.Usecase
import br.org.acal.infrastructure.Sl4jLogger
import org.springframework.stereotype.Service

@Service
class CustomerCreateLotUsecase(
    private val dataSource: CustomerDataSource
) : Usecase<List<Customer>, Unit>, Sl4jLogger()  {

    override fun execute(input: List<Customer>) {
        dataSource.saveAll(input)
    }

}
