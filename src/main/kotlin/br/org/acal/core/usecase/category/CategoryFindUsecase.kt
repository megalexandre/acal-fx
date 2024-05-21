package br.org.acal.core.usecase.category

import br.org.acal.core.datasource.CategoryDataSource
import br.org.acal.core.entity.Category
import br.org.acal.core.entity.CategoryFilter
import br.org.acal.core.entity.DefaultFilter
import br.org.acal.core.entity.PageFilter
import br.org.acal.core.usecase.Usecase
import org.springframework.stereotype.Service


@Service
class CategoryFindUsecase(
    private val dataSource: CategoryDataSource
) : Usecase<CategoryFilter, Collection<Category>> {
    override fun execute(input: CategoryFilter) = dataSource.findByFilter(input)

}
