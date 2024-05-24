package br.org.acal.core.usecase.customer

import br.org.acal.application.controller.customer.input.CustomerPageFilter
import br.org.acal.core.datasource.CustomerDataSource
import br.org.acal.core.entity.Customer
import br.org.acal.core.entity.PageFilter
import br.org.acal.core.usecase.Usecase
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service


@Service
class CustomerPaginateUsecase(
    private val dataSource: CustomerDataSource
) : Usecase<CustomerPageFilter, Page<Customer>> {
    override fun execute(input: CustomerPageFilter) = dataSource.paginateByFilter(input)

}
