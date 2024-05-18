package br.org.acal.core.usecase.category

import br.org.acal.core.datasource.CategoryDataSource
import br.org.acal.core.entity.Category
import br.org.acal.core.entity.PageFilter
import br.org.acal.core.usecase.Usecase
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service


@Service
class CategorySaveUsecase(
    private val dataSource: CategoryDataSource
) : Usecase<Category, Category> {
    override fun execute(input: Category) = dataSource.save(input)

}
