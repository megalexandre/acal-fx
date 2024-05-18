package br.org.acal.core.usecase.customer

import br.org.acal.core.datasource.CustomerDataSource
import br.org.acal.core.entity.Customer
import br.org.acal.core.usecase.Usecase
import br.org.acal.infrastructure.Sl4jLogger
import br.org.acal.infrastructure.info
import org.springframework.stereotype.Service

@Service
class CustomerCreateUsecase(
    private val dataSource: CustomerDataSource
) : Usecase<Customer, Customer> , Sl4jLogger() {

    override fun execute(input: Customer): Customer =
        valid(input).let {
            dataSource.save(input)
        }

    private fun valid(customer: Customer) {
        dataSource.findByDocument(customer.documentNumber)?.let {
            logger.info { "Already exists a customer with document ${customer.documentNumber.number}" }

            throw RuntimeException(
                "Already exists a customer with document ${customer.documentNumber.number}"
            )
        }
    }

}
