package br.org.acal.resources.gateway.category

import br.org.acal.commons.enums.CategoryType
import br.org.acal.core.datasource.CategoryDataSource
import br.org.acal.core.entity.Category
import br.org.acal.core.entity.CategoryFilter
import br.org.acal.core.entity.DefaultFilter
import br.org.acal.core.entity.PageFilter
import br.org.acal.resources.gateway.category.data.input.CategoryPaginateGatewayResponse
import br.org.acal.resources.gateway.category.data.output.toCategoryCreateRequest
import br.org.acal.resources.gateway.category.data.output.toCategoryUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class CategoryDatasourceImpl (
    val categoryGateway: CategoryGateway
): CategoryDataSource {
    override fun save(t: Category): Category {
        categoryGateway.save(t.toCategoryCreateRequest())
        return t
    }

    override fun update(t: Category): Category {
        categoryGateway.update(t.toCategoryUpdateRequest())
        return t
    }

    override fun saveAll(t: Collection<Category>) {
        TODO("Not yet implemented")
    }

    override fun delete(id: String) {
        categoryGateway.delete(id)
    }

    override fun findAll(): Collection<Category> {
        TODO("Not yet implemented")
    }

    override fun findById(id: String): Category? =
        categoryGateway.findById(id).toCategory()

    override fun findByFilter(filter: DefaultFilter): List<Category> =
        (filter as CategoryFilter).let {
            categoryGateway.findByFilter(
                id = it.id,
                name = it.name,
                type = CategoryType.of(it.type)?.name ,
            )
        }.map { it.toCategory() }

    override fun paginateByFilter(filter: PageFilter): Page<Category> =
        categoryGateway.paginate().let { responsePage ->
            PageImpl(
                responsePage.content.map(CategoryPaginateGatewayResponse::toCategory),
                PageRequest.of(responsePage.number, responsePage.size, responsePage.sort),
                responsePage.totalElements
            )
        }

}