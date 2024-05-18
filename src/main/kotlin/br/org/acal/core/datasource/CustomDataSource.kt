package br.org.acal.core.datasource

import br.org.acal.core.entity.DefaultFilter
import br.org.acal.core.entity.PageFilter
import org.springframework.data.domain.Page

interface CustomDataSource<T> {
    fun save(t: T): T
    fun update(t: T): T
    fun saveAll(t: Collection<T>)
    fun delete(id: String)
    fun findAll(): Collection<T>
    fun findById(id: String): T?
    fun findByFilter(filter: DefaultFilter): List<T>
    fun paginateByFilter(filter: PageFilter): Page<T>
}

