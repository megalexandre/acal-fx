package br.org.acal.core.datasource

import br.org.acal.core.entity.Customer
import br.org.acal.core.entity.DocumentNumber

interface CustomerDataSource: CustomDataSource<Customer> {
    fun findByDocument(documentNumber: DocumentNumber): Customer?
}