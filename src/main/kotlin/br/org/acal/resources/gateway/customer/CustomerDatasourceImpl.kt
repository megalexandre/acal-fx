package br.org.acal.resources.gateway.customer

import br.org.acal.application.controller.customer.input.CustomerPageFilter
import br.org.acal.core.datasource.CustomerDataSource
import br.org.acal.core.entity.Customer
import br.org.acal.core.entity.DefaultFilter
import br.org.acal.core.entity.DocumentNumber
import br.org.acal.core.entity.PageFilter
import br.org.acal.resources.gateway.customer.data.CustomerPaginateGatewayResponse
import br.org.acal.resources.gateway.customer.data.toCustomer
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class CustomerDatasourceImpl (
    val customerGateway: CustomerGateway
): CustomerDataSource {
    override fun findByDocument(documentNumber: DocumentNumber): Customer? {
        TODO("Not yet implemented")
    }

    override fun save(t: Customer): Customer {
        TODO("Not yet implemented")
    }

    override fun update(t: Customer): Customer {
        TODO("Not yet implemented")
    }

    override fun saveAll(t: Collection<Customer>) {
        TODO("Not yet implemented")
    }

    override fun delete(id: String) {
        TODO("Not yet implemented")
    }

    override fun findAll(): Collection<Customer> =
        customerGateway.find().toCustomer()

    override fun findById(id: String): Customer? {
        TODO("Not yet implemented")
    }

    override fun findByFilter(filter: DefaultFilter): List<Customer> {
        TODO("Not yet implemented")
    }

    override fun paginateByFilter(filter: PageFilter): Page<Customer> =
        customerGateway.paginate(
            id = (filter as CustomerPageFilter).filter.id,
            name = filter.filter.name,
            documentNumber = filter.filter.documentNumber,
            offset = filter.limitOffsetAndSort.offset.toInt(),
            size = filter.limitOffsetAndSort.size.toInt(),
            field = filter.limitOffsetAndSort.field,
            direction = filter.limitOffsetAndSort.direction,
        ).let { responsePage ->
            PageImpl(
                responsePage.content.map(CustomerPaginateGatewayResponse::toCustomer),
                PageRequest.of(responsePage.number, responsePage.size, responsePage.sort),
                responsePage.totalElements
            )
        }

}